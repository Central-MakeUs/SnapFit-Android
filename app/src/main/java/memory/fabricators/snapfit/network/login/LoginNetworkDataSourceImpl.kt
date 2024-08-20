package memory.fabricators.snapfit.network.login

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import memory.fabricators.snapfit.network.login.model.SocialSignUpRequest
import memory.fabricators.snapfit.network.login.model.TokenResponse

class LoginNetworkDataSourceImpl(
    private val httpClient: HttpClient,
) : LoginNetworkDataSource() {
    override suspend fun login(token: String): TokenResponse {
        val response = httpClient.get("/snapfit/login") {
            bearerAuth(token)
            parameter("SocialType", "kakao")
        }

        return response.body()
    }

    override suspend fun logout(token: String) {
        // TODO()
    }

    override suspend fun socialSignUp(
        req: SocialSignUpRequest,
        socialAccessToken: String,
    ): TokenResponse {
        val response = httpClient.post("/snapfit/user") {
            bearerAuth(socialAccessToken)
            setBody(req)
        }
        return response.body()
    }

    override suspend fun reissueToken(): TokenResponse {
        return TODO()
    }
}
