package com.zuhaib.examPortalUserService.service.impl

import com.fasterxml.uuid.Generators
import com.zuhaib.examPortalUserService.model.User
import com.zuhaib.examPortalUserService.repo.UserRepository
import com.zuhaib.examPortalUserService.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(
    @Autowired
    var userRepository: UserRepository,
    @Autowired
    var mailSender: JavaMailSender

) : UserService {
    @Autowired
    var env: Environment? = null

    @Throws(Exception :: class)
    override fun createUser(user: User): User? {
        if (userRepository.findByUsername(user.getUsername()) !=  null){
            println("User Already there")
            throw Exception("User Present")
        }else{
            val uuid : UUID = Generators.timeBasedGenerator().generate()
            user.setUserId( uuid.toString())
            user.setRole("NORMAL")
            user.setSubscribed(false)
            userRepository.save(user)

            try {
                val message = SimpleMailMessage()
                message.setFrom("zuhaibahmed444@gmail.com")
                message.setSubject("Signup Success at ExamPortal")
                message.setTo(user.getEmail())
                message.setText("""
             welcome to Exam portal
             Username:${user.username}
             Email id:${user.getEmail()}
             """.trimIndent())
                mailSender.send(message)
                println("Signup Sucess mail sent")
            }catch (e:Exception){
                println(e)
            }


        }

        return userRepository.save(user)
    }



    override fun getUser(username: String): User? = userRepository.findByUsername(username)
    override fun updateUser(user: User): User? {
        return userRepository.save(user)
    }

    override fun getUsers(): List<User?>? {
        return userRepository.findAll()
    }

    override fun setUserSubscribed(username: String): User? {
        val user : User = userRepository.findByUsername(username)!!
        user.setSubscribed(true)
        userRepository.save(user)
        try {
            val message = SimpleMailMessage()
            message.setFrom("zuhaibahmed444@gmail.com")
            message.setSubject("Subscription Successful")
            message.setTo(user.getEmail())
            message.setText("""
             Successfully Subscribed to Exam portal
             Username:${user.username}
             Email id:${user.getEmail()}
             Membership Validity : LifeTime
             """.trimIndent())
            mailSender.send(message)
            println("Signup Sucess mail sent")

        }catch (e : Exception){
            println(e)
        }

        return userRepository.save(user)
    }


}