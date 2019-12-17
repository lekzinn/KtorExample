package com.example

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing

fun Application.post() {
    install(ContentNegotiation) {
        gson { setPrettyPrinting() }
    }

    routing {
        post("/user") {
            val user = call.receive<User>()
            println(user)
            call.respondText("Hello User! $user", ContentType.Text.Plain)
        }

        get("/user/{name}") {
            val name = call.parameters["name"]
            val user = User(name!!, 27)
            call.respond(user)
        }
    }
}

data class User(val name: String, val age: Int)