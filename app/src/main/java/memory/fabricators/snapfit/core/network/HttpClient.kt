package memory.fabricators.snapfit.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import memory.fabricators.snapfit.BuildConfig

val httpClient = HttpClient(CIO) {
    defaultRequest {
        contentType(ContentType.Application.Json)
        url(BuildConfig.BASE_URL)
    }
    install(ContentNegotiation) {
        json()
    }
    install(Logging) {
        level = LogLevel.ALL
        logger = object : Logger {
            override fun log(message: String) {
                println(message)
            }
        }
    }
}
