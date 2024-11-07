package ru.dumdumbich.android.steward.server.iot

import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import ru.dumdumbich.android.steward.server.iot.api.routeHello
import ru.dumdumbich.android.steward.server.iot.api.routeMessage

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }
    routeHello()
    routeMessage()
}
