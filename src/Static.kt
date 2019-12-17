package com.example

import io.ktor.application.Application
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.routing.routing

fun Application.static() {
    routing {
        static("/static") {
            resources("static")
        }
    }
}