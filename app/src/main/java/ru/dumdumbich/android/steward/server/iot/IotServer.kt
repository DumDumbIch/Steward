package ru.dumdumbich.android.steward.server.iot

import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.netty.NettyApplicationEngine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

object IotServer {

    private val serverScope: CoroutineScope = CoroutineScope(
        SupervisorJob() + Dispatchers.IO
    )
    private lateinit var server: NettyApplicationEngine

    fun start() {
        serverScope.launch {
            server = embeddedServer(Netty, port = 8080, module = Application::module)
            server.start(wait = false)
        }
    }

    fun stop() {
        serverScope.launch {
            server.stop(0, 0)
        }
    }
}
