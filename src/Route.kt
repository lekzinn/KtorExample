package com.example

import io.ktor.application.Application
import io.ktor.routing.*

fun Application.route() {
    routing {
        route("/api") {
            route("/user") { userController() }
            route("/company") { companyController() }
            route("/product") { }
            route("/transaction") { }
        }
    }
}

fun Route.userController() {
    post { }

    route("/{id}") {
        get { }
        put { }
        delete { }
    }

    route("/list") {
        get { }
    }
}

fun Route.companyController() {
    post { }

    route("/list") {
        get { }
    }

    route("/{id}") {
        get { }
        put { }
    }
}