package com.zuhaib.examPortalUserService.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.zuhaib.examPortalUserService.model.User
import com.zuhaib.examPortalUserService.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/user")
class UserController(
    @Autowired
    val userService: UserService,
    @Autowired
    private val bCryptPasswordEncoder: BCryptPasswordEncoder

) {

    val mapper = ObjectMapper()

    //create user
    @PostMapping("/")
    fun createUser(@RequestBody user: User): String? {
        user.password = bCryptPasswordEncoder.encode(user.password)
        return mapper.writeValueAsString(userService.createUser(user))
    }

    @GetMapping("/{username}")
    fun getUsers(@PathVariable("username") username: String): String? {
        return mapper.writeValueAsString(userService.getUser(username))
    }
    @GetMapping("/")
    fun getUser():ResponseEntity<*>{
        println(userService.getUsers()?.size)
        return ResponseEntity.ok(userService.getUsers())
    }
    @GetMapping("/count")
    fun getUserCount():ResponseEntity<*>{
        return ResponseEntity.ok(userService.getUsers()?.size)
    }
    @PutMapping("/setsubcribed")
    fun setSubcribed(@RequestBody username: String):ResponseEntity<*>{
        return ResponseEntity.ok(userService.setUserSubscribed(username))
    }
}
