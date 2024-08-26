package memory.fabricators.snapfit.network.post

import memory.fabricators.snapfit.network.post.model.PostDetailsResponse
import memory.fabricators.snapfit.network.post.model.PostLikeCountResponse
import memory.fabricators.snapfit.network.post.model.PostListResponse
import memory.fabricators.snapfit.network.post.model.PostRequest
import memory.fabricators.snapfit.network.post.model.PostResponse


abstract class PostNetworkDataSource {
    abstract suspend fun getLikedPosts(postId: String): PostListResponse
    abstract suspend fun getMakerPosts(makerId: String): PostListResponse
    abstract suspend fun getPostDetails(postId: Long): PostDetailsResponse
    abstract suspend fun createPost(postRequest: PostRequest): PostResponse
    abstract suspend fun likePost(postId: String)
    abstract suspend fun unlikePost(postId: String)
    abstract suspend fun getPostLikeCount(postId: String): PostLikeCountResponse
    abstract suspend fun getAllPosts(): PostListResponse
    abstract suspend fun getPostsByVibes(vibes: List<String>): PostListResponse
}