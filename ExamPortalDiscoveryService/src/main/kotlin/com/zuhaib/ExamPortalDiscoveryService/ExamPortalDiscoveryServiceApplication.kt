package com.zuhaib.ExamPortalDiscoveryService

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class ExamPortalDiscoveryServiceApplication

fun main(args: Array<String>) {
	runApplication<ExamPortalDiscoveryServiceApplication>(*args)
}
