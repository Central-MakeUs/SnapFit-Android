package memory.fabricators.snapfit.network.login

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import memory.fabricators.snapfit.network.login.model.TokenResponse

class LoginNetworkDataSourceImpl(
    private val httpClient: HttpClient,
) : LoginNetworkDataSource() {
    override suspend fun login(token: String): TokenResponse {
        val response = httpClient.get("/snapfit/login") {
            header("Authorization", "Bearer $token")
            parameter("SocialType", "kakao")
        }

        return response.body()
    }

    override suspend fun socialSignUp(token: String): TokenResponse {
        TODO("Not yet implemented")
    }

    override suspend fun reissueToken(): TokenResponse {
        return TODO()
    }
}
