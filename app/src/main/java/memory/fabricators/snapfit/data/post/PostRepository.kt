package memory.fabricators.snapfit.data.post

import memory.fabricators.snapfit.data.post.model.PostDetails
import memory.fabricators.snapfit.data.post.model.PostList

abstract class PostRepository {
    abstract suspend fun getLikedPosts(postId: String): PostList
    abstract suspend fun getMakerPosts(
        makerId: Long,
        offset: Int,
        limit: Int,
    ): PostList

    abstract suspend fun getPostDetails(postId: Long): PostDetails
    abstract suspend fun createPost(
        vibes: List<String>,
        locations: List<String>,
        imageNames: List<String>,
        thumbnail: String,
        title: String,
        desc: String,
        prices: List<PostDetails.Prices>,
        personPrice: Long,
        studio: Boolean,
    ): PostDetails

    abstract suspend fun likePost(postId: String)
    abstract suspend fun unlikePost(postId: String)
    abstract suspend fun getPostLikeCount(): Int
    abstract suspend fun getAllPosts(): PostList
    abstract suspend fun getPostsByVibes(vibes: List<String>): PostList
}
