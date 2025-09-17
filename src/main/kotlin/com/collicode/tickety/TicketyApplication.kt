package com.collicode.tickety

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = [
        "com.collicode.tickety",
        "com.collicode.common"
    ]
)
class TicketyApplication

fun main(args: Array<String>) {
	runApplication<TicketyApplication>(*args)
}
