package com.zuhaib.examPortalExamService

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@SpringBootApplication
class ExamPortalExamServiceApplication

fun main(args: Array<String>) {
	runApplication<ExamPortalExamServiceApplication>(*args)
}

