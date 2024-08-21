package memory.fabricators.snapfit.network.post

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import memory.fabricators.snapfit.network.post.model.PostDetailsResponse
import memory.fabricators.snapfit.network.post.model.PostLikeCountResponse
import memory.fabricators.snapfit.network.post.model.PostListResponse
import memory.fabricators.snapfit.network.post.model.PostRequest
import memory.fabricators.snapfit.network.post.model.PostResponse

class PostNetworkDataSourceImpl(
    private val httpClient: HttpClient,
) : PostNetworkDataSource() {
    override suspend fun getPostLikes(postId: String): PostListResponse {
        val response = httpClient.get("/snapfit/post/$postId/likes")
        return response.body()
    }

    override suspend fun getMakerPosts(makerId: String): PostListResponse {
        val response = httpClient.get("/snapfit/maker/$makerId/posts")
        return response.body()
    }

    override suspend fun getPostDetails(postId: String): PostDetailsResponse {
        val response = httpClient.get("/snapfit/post/$postId")
        return response.body()
    }

    override suspend fun createPost(postRequest: PostRequest): PostResponse {
        val response = httpClient.post("/snapfit/post")
        return response.body()
    }

    override suspend fun likePost(postId: String) {
        httpClient.post("/snapfit/post/$postId/like")
    }

    override suspend fun unlikePost(postId: String) {
        httpClient.delete("/snapfit/post/$postId/like")
    }

    override suspend fun getPostLikeCount(postId: String): PostLikeCountResponse {
        val response = httpClient.get("/snapfit/post/$postId/like/count")
        return response.body()
    }

    override suspend fun getAllPosts(): PostListResponse {
        val response = httpClient.get("/snapfit/posts")
        return response.body()
    }

    override suspend fun getPostsByVibes(vibes: List<String>): PostListResponse {
        val response = httpClient.get("/snapfit/posts/vibes") {}
        return response.body()
    }
}