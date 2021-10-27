package com.zuhaib.examPortalUserService.config

import com.zuhaib.examPortalUserService.service.impl.UserDetailsServiceImpl
import io.jsonwebtoken.ExpiredJwtException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationFilter : OncePerRequestFilter() {

    @Autowired
    private val userDetailsServiceImpl: UserDetailsServiceImpl?=null

    @Autowired
    private val jwtUtil: JwtUtils? = null

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val requestTokenHeader = request.getHeader("Authorization")
        println(requestTokenHeader)
        var username: String? = null
        var jwtToken: String? = null
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            //yes
            jwtToken = requestTokenHeader.substring(7)
            try {
                username = jwtUtil!!.extractUsername(jwtToken)
            } catch (e: ExpiredJwtException) {
                e.printStackTrace()
                println("jwt token has expired")
            } catch (e: Exception) {
                e.printStackTrace()
                println("error")
            }
        } else {
            println("Invalid token , not start with bearer string")
        }
        //validated
        if (username != null && SecurityContextHolder.getContext().authentication == null) {
            val userDetails = userDetailsServiceImpl!!.loadUserByUsername(username)
            if (jwtUtil!!.validateToken(jwtToken, userDetails)) {
                //token is valid
                val usernamePasswordAuthentication =
                    UsernamePasswordAuthenticationToken(userDetails, null, userDetails?.authorities)
                usernamePasswordAuthentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = usernamePasswordAuthentication
            }
        } else {
            println("Token is not valid")
        }
        filterChain.doFilter(request, response)
    }


}
