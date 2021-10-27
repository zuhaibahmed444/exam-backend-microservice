package com.zuhaib.examPortalUserService

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@SpringBootApplication
class ExamPortalUserServiceApplication

fun main(args: Array<String>) {
	runApplication<ExamPortalUserServiceApplication>(*args)
}
