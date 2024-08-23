package memory.fabricators.snapfit.network.user

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import memory.fabricators.snapfit.core.token.TokenManager
import memory.fabricators.snapfit.data.user.model.Vibe
import memory.fabricators.snapfit.network.user.model.LocationResponse
import memory.fabricators.snapfit.network.user.model.UpdateUserInfoRequest
import memory.fabricators.snapfit.network.user.model.UserInfoResponse

class UserNetworkDataSourceImpl(
    private val httpClient: HttpClient, // Replace with your HTTP client
    private val tokenManager: TokenManager,
) : UserNetworkDataSource() {

    override suspend fun getLocations(): List<LocationResponse> {
        val response = httpClient.get("/snapfit/locations") {
            // Add necessary headers or parameters if required
        }
        return response.body() // Replace with proper deserialization
    }

    override suspend fun getUserInfo(): UserInfoResponse {
        val response = httpClient.get("/snapfit/user") {
            bearerAuth(tokenManager.accessToken)
        }
        return response.body()
    }

    override suspend fun deleteUser() {
        httpClient.delete("/snapfit/user") {
        }
    }

    override suspend fun updateUserInfo(req: UpdateUserInfoRequest): UserInfoResponse {
        val response = httpClient.post("/snapfit/user/info") {
            setBody(req)
        }
        return response.body() // Replace with proper deserialization
    }

    override suspend fun fetchVibes(): List<Vibe> {
        val response = httpClient.get("/snapfit/vibes") {
        }
        return response.body() // Replace with proper deserialization
    }
}