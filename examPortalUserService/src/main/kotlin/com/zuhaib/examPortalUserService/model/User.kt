package com.zuhaib.examPortalUserService.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Document(collection = "user")
class User: UserDetails{
    @Id
    private var userId:String = ""
    private var username :String=" "
    private var password :String=" "
    private var firstName:String=" "
    private var lastName:String=" "
    private var email:String=" "
    private var phone :String=""
    private var enabled:Boolean=true
    private var subscribed:Boolean? =null
    private var role :String =" "
    private var profileLocation:String=""
    private var profileFileName :String=""



    constructor(
        username: String,
        password: String,
        firstName: String,
        lastName: String,
        email: String,
        phone: String,
    ) {
        this.username = username
        this.password = password
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.phone = phone
    }
    fun setProfileLocation(profileLocation :String){
        this.profileLocation = profileLocation
    }
    fun getProfileLocation():String{
        return profileLocation
    }

    fun setProfileFileName(profileFileName :String){
        this.profileFileName = profileFileName
    }
    fun getProfileFileName():String{
        return profileFileName
    }

    fun setSubscribed(subscribe:Boolean){
        this.subscribed = subscribe
    }
    fun getSubscribed(): Boolean? {
        return subscribed
    }

    fun setRole(role:String){
        this.role = role
    }
    fun getRole():String{
        return role
    }
    fun setUserId(userId :String){
        this.userId = userId
    }

    fun getUserId():String{
        return userId
    }
    fun setUsername(username:String){
        this.username = username
    }
    fun setPassword(password:String){
        this.password = password
    }
    fun setFirstName(firstName:String){
        this.firstName = firstName
    }
    fun setLastName(lastName:String){
        this.lastName = lastName
    }
    fun setEmail(email:String){
        this.email = email
    }

    fun setPhone(phone:String){
        this.phone = phone
    }
    fun setEnabled(enabled:Boolean){
        this.enabled = enabled
    }
    override fun getUsername():String{
        return  username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getPassword():String{
        return password
    }
    fun getFirstName():String{
        return firstName
    }
    fun getLastName():String{
        return lastName
    }
    fun getEmail():String{
        return email
    }
    fun getPhone():String{
        return  phone
    }
    fun getEnabled():Boolean{
        return  enabled
    }



    override fun toString(): String {
        return "User(userId='$userId', username='$username', password='$password', firstName='$firstName', lastName='$lastName', email='$email', phone='$phone', enabled=$enabled, role=$role)"
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val set:MutableSet<Authority> = HashSet()
        set.add(Authority(this.role))

        return set
    }

}


