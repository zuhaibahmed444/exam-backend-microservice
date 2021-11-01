package com.zuhaib.examPortalUserService

import com.zuhaib.examPortalUserService.model.User
import com.zuhaib.examPortalUserService.repo.UserRepository
import com.zuhaib.examPortalUserService.service.UserService
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest {
    @Autowired
    val service : UserService? =null

    @Mock
    val userRepository : UserRepository?=null

    val user: User = User("zuhaibahmednotr","fdhgshg","zuhajj","gysgygs","zuhaib@email","5666" )

    @Test
    fun saveUserTest() {
        `when`(userRepository!!.save(user)).thenReturn(user)
        assertEquals(user,service?.createUser(user))


    }

    @Test
    fun getUserTest() {
        val username = "zuhaibahmednotr"
        `when`(userRepository?.findByUsername(username))
            .thenReturn(user)
        assertEquals(user.getLastName(), service?.getUser(username)?.getLastName())
    }

    @Test
    fun getSubcribedTest(){
        val username :String = "zuhaibahmednotr"
        val uss = service?.setUserSubscribed(username)
        uss?.getSubscribed()?.let { assertTrue(it) }
    }
}