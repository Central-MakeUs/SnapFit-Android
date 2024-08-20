package memory.fabricators.snapfit.network.user

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import memory.fabricators.snapfit.data.user.model.Vibe
import memory.fabricators.snapfit.network.login.model.TokenResponse
import memory.fabricators.snapfit.network.user.model.SignUpRequest

class UserNetworkDataSourceImpl(
    private val httpClient: HttpClient,
) : UserNetworkDataSource() {
    override suspend fun fetchVibes(): List<Vibe> {
        val response = httpClient.get("/snapfit/vibes")
        return response.body()
    }

    override suspend fun signUp(
        req: SignUpRequest,
        socialAccessToken: String,
    ): TokenResponse {
        val response = httpClient.post("/snapfit/user") {
            bearerAuth(socialAccessToken)
            setBody(req)
        }
        return response.body()
    }
}
