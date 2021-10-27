package com.zuhaib.examPortalUserService.model

class JwtRequest {
    private var username :String =""
    private var password :String=" "

    constructor(username: String, password: String) {
        this.username = username
        this.password = password
    }

    fun setUsername(username: String){
        this.username = username
    }
    fun getUsername():String{
        return  username
    }

    fun setPassword(password: String){
        this.password = password
    }

    fun getPassword():String{
        return password
    }
}