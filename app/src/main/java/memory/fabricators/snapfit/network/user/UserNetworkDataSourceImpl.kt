package memory.fabricators.snapfit.network.user

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import memory.fabricators.snapfit.data.user.model.Vibe

class UserNetworkDataSourceImpl(
    private val httpClient: HttpClient,
) : UserNetworkDataSource() {
    override suspend fun fetchVibes(): List<Vibe> {
        val response = httpClient.get("/snapfit/vibes")
        return response.body()
    }
}
