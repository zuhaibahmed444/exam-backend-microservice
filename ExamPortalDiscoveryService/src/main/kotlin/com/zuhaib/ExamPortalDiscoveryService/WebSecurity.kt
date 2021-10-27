package com.zuhaib.ExamPortalDiscoveryService

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import kotlin.jvm.Throws


@EnableWebSecurity
class WebSecurity : WebSecurityConfigurerAdapter() {

    @Override
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .csrf()
            .disable()
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .httpBasic();
    }

}