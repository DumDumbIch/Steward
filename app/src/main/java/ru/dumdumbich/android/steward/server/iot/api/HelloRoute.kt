package ru.dumdumbich.android.steward.server.iot.api

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun Application.routeHello() {
    routing {
        get("/api") {
            call.respondText("Hello World!")
        }
    }
}
