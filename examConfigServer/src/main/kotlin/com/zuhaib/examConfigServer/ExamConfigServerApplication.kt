package com.zuhaib.examConfigServer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
class ExamConfigServerApplication

fun main(args: Array<String>) {
	runApplication<ExamConfigServerApplication>(*args)
}
