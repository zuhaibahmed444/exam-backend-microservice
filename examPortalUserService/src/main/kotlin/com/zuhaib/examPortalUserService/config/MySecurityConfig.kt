package com.zuhaib.examPortalUserService.config

import com.zuhaib.examPortalUserService.service.impl.UserDetailsServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import kotlin.jvm.Throws

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class MySecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private val unauthorizedHandler: JwtAuthenticationEntryPoint? = null

    @Autowired
    private val jwtAuthenticationFilter: JwtAuthenticationFilter? = null
    @Autowired
    private val userDetailsServiceImpl :UserDetailsServiceImpl? =null

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder?) {

        auth?.userDetailsService(userDetailsServiceImpl)?.passwordEncoder(passwordEncoder())
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity?) {
        http
            ?.csrf()
            ?.disable()
            ?.cors()
            ?.disable()
            ?.authorizeRequests()
            ?.antMatchers("/api/v1/generate-token", "/api/v1/user/","/swagger-ui/**","/v2/api-docs/**","/swagger-resources/**","/webjars/**")?.permitAll()
            ?.antMatchers(HttpMethod.OPTIONS)?.permitAll()
            ?.anyRequest()?.authenticated()
            ?.and()
            ?.exceptionHandling()?.authenticationEntryPoint(unauthorizedHandler)
            ?.and()
            ?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http?.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}