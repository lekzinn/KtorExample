package com.example

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.UserIdPrincipal
import io.ktor.auth.authenticate
import io.ktor.auth.basic
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing

fun Application.authentication() {

    install(Authentication) {
        basic("auth") {
            realm = "Auth Sample"
            validate { credencials ->
                if (credencials.name == credencials.password) {
                    UserIdPrincipal(credencials.name)
                } else {
                    null
                }

            }
        }
    }

    routing {
        get("/") {
            call.respondText("You need credentials to acess /secure")
        }

        authenticate("auth") {
            get("/secure") {
                call.respondText("Accessed secure area")
            }
        }
    }
}