package com.zuhaib.examPortalUserService.service

import com.zuhaib.examPortalUserService.model.User

interface UserService {
    fun createUser(user : User): User?
    //get user by username
    fun getUser(username: String): User?
    //update
    fun updateUser(user:User) :User?
    //get user
    fun getUsers():List<User?>?
    //set as subcribed
    fun setUserSubscribed(username: String): User?
}