package memory.fabricators.snapfit.data.post.model

data class PostList(
    val offset: Int,
    val limit: Int,
    val data: List<Post>,
) {
    data class Post(
        val id: Long,
        val maker: Maker,
        val title: String,
        val thumbnail: String,
        val vibes: List<String>,
        val locations: List<String>,
        val price: Int,
        val studio: Boolean,
        val like: Boolean,
    ) {
        data class Maker(
            val id: Long,
            val nickname: String,
        )
    }
}
