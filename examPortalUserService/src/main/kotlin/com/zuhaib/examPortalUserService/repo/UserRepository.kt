package com.zuhaib.examPortalUserService.repo

import com.zuhaib.examPortalUserService.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : MongoRepository<User,String>{
    fun findByUsername(username: String?): User?
}