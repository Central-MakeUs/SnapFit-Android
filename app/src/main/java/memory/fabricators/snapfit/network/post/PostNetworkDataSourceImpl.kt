package memory.fabricators.snapfit.network.post

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import memory.fabricators.snapfit.core.token.TokenManager
import memory.fabricators.snapfit.network.post.model.PostDetailsResponse
import memory.fabricators.snapfit.network.post.model.PostLikeCountResponse
import memory.fabricators.snapfit.network.post.model.PostListResponse
import memory.fabricators.snapfit.network.post.model.PostRequest
import memory.fabricators.snapfit.network.post.model.PostResponse

class PostNetworkDataSourceImpl(
    private val httpClient: HttpClient,
    private val tokenManager: TokenManager,
) : PostNetworkDataSource() {
    override suspend fun getLikedPosts(postId: String): PostListResponse {
        val response = httpClient.get("/snapfit/post/$postId/likes?limit=20&offset=0") {
            bearerAuth(token = tokenManager.accessToken)
        }
        return response.body()
    }

    override suspend fun getMakerPosts(makerId: String): PostListResponse {
        val response = httpClient.get("/snapfit/maker/$makerId/posts?limit=20&offset=0") {
            bearerAuth(token = tokenManager.accessToken)
        }
        return response.body()
    }

    override suspend fun getPostDetails(postId: String): PostDetailsResponse {
        val response = httpClient.get("/snapfit/post/$postId") {
            bearerAuth(token = tokenManager.accessToken)
        }
        return response.body()
    }

    override suspend fun createPost(postRequest: PostRequest): PostResponse {
        val response = httpClient.post("/snapfit/post") {
            bearerAuth(token = tokenManager.accessToken)
        }
        return response.body()
    }

    override suspend fun likePost(postId: String) {
        httpClient.post("/snapfit/post/$postId/like") {
            bearerAuth(token = tokenManager.accessToken)
        }
    }

    override suspend fun unlikePost(postId: String) {
        httpClient.delete("/snapfit/post/$postId/like") {
            bearerAuth(token = tokenManager.accessToken)
        }
    }

    override suspend fun getPostLikeCount(postId: String): PostLikeCountResponse {
        val response = httpClient.get("/snapfit/post/$postId/like/count") {
            bearerAuth(token = tokenManager.accessToken)
        }
        return response.body()
    }

    override suspend fun getAllPosts(): PostListResponse {
        val response = httpClient.get("/snapfit/posts?limit=20&offset=1") {
            bearerAuth(token = tokenManager.accessToken)
        }
        return response.body()
    }

    override suspend fun getPostsByVibes(vibes: List<String>): PostListResponse {
        val response = httpClient.get("/snapfit/posts/vibes") {
            bearerAuth(token = tokenManager.accessToken)
        }
        return response.body()
    }
}
