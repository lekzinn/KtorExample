package com.example

import io.ktor.application.*
import io.ktor.response.header
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.util.AttributeKey
import io.ktor.util.pipeline.PipelineContext


class CustomHeader(configuration: Configuration) {
    // get an immutable snapshot of a configuration values
    private val name = configuration.headerName
    private val value = configuration.headerValue

    // Feature configuration class
    class Configuration {
        // mutable properties with default values so user can modify it
        var headerName = "Custom"
        var headerValue = "Value"
    }

    // Body of the feature
    private fun intercept(context: PipelineContext<Unit, ApplicationCall>) {
        // Add custom header to the response
        context.call.response.header(name, value)
    }

    companion object Feature : ApplicationFeature<ApplicationCallPipeline, Configuration, CustomHeader> {
        override val key = AttributeKey<CustomHeader>("CustomHeader")
        override fun install(pipeline: ApplicationCallPipeline, configure: Configuration.() -> Unit): CustomHeader {
            // Call user code to configure a feature
            val configuration = Configuration().apply(configure)

            // Create a feature instance
            val feature = CustomHeader(configuration)

            // Install an interceptor that will be run on each call and call feature instance
            pipeline.intercept(ApplicationCallPipeline.Call) {
                feature.intercept(this)
            }

            // Return a feature instance so that client code can use it
            return feature
        }
    }
}


fun Application.customFeature() {

    install(CustomHeader) {
        headerName = "Hello"
        headerValue = "World"
    }

    routing {
        get("/") {
            call.respondText("Check Headers!!")
        }

    }
}