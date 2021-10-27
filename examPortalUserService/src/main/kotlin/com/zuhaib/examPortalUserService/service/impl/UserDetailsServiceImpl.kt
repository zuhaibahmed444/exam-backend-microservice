package com.zuhaib.examPortalUserService.service.impl

import com.zuhaib.examPortalUserService.repo.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import kotlin.jvm.Throws

@Service
class UserDetailsServiceImpl(private val userRepository: UserRepository) : UserDetailsService{

    @Throws(Exception::class)
    override fun loadUserByUsername(username: String?): UserDetails? {
        val user = this.userRepository.findByUsername(username)
        if (user == null){
            println("User Not Found")
            throw Exception("Invalid User")
        }
        return user

    }

}