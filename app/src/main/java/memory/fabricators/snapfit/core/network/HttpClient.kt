package memory.fabricators.snapfit.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.defaultRequest
import memory.fabricators.snapfit.BuildConfig

val httpClient = HttpClient(CIO) {
    defaultRequest {
        url(BuildConfig.BASE_URL)
    }
}
