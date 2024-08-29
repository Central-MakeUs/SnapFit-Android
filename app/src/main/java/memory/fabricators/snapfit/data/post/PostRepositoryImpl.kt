package memory.fabricators.snapfit.data.post

import memory.fabricators.snapfit.data.post.model.PostDetails
import memory.fabricators.snapfit.data.post.model.PostList
import memory.fabricators.snapfit.network.post.PostNetworkDataSource
import memory.fabricators.snapfit.network.post.model.PostRequest

class PostRepositoryImpl(
    private val postNetworkDataSource: PostNetworkDataSource,
) : PostRepository() {
    override suspend fun getLikedPosts(postId: String): PostList {
        val response = postNetworkDataSource.getLikedPosts(postId)
        return with(response) {
            PostList(
                offset = offset,
                limit = limit,
                data = data.map {
                    PostList.Post(
                        id = it.id,
                        maker = PostList.Post.Maker(
                            id = it.maker.id,
                            nickname = it.maker.nickname,
                        ),
                        title = it.title,
                        thumbnail = it.thumbnail,
                        vibes = it.vibes,
                        locations = it.locations,
                        price = it.price,
                        studio = it.studio,
                        like = it.like,
                    )
                },
            )
        }
    }

    override suspend fun getMakerPosts(
        makerId: Long,
        offset: Int,
        limit: Int,
    ): PostList {
        val response = postNetworkDataSource.getMakerPosts(
            makerId = makerId,
            offset = offset,
            limit = limit,
        )
        return with(response) {
            PostList(
                offset = offset,
                limit = limit,
                data = data.map {
                    PostList.Post(
                        id = it.id,
                        maker = PostList.Post.Maker(
                            id = it.maker.id,
                            nickname = it.maker.nickname,
                        ),
                        title = it.title,
                        thumbnail = it.thumbnail,
                        vibes = it.vibes,
                        locations = it.locations,
                        price = it.price,
                        studio = it.studio,
                        like = it.like,
                    )
                },
            )
        }
    }

    override suspend fun getPostDetails(postId: Long): PostDetails {
        val response = postNetworkDataSource.getPostDetails(postId)
        return with(response) {
            PostDetails(
                id = id,
                maker = PostDetails.Maker(
                    id = maker.id,
                    nickname = maker.nickname,
                ),
                title = title,
                thumbnail = thumbnail,
                images = images,
                vibes = vibes,
                locations = locations,
                studio = studio,
                like = like,
                createdAt = createAt,
                prices = prices.map {
                    PostDetails.Prices(
                        min = it.min,
                        price = it.price,
                    )
                },
                personPrice = personPrice,
                desc = desc,
            )
        }
    }

    override suspend fun createPost(
        vibes: List<String>,
        locations: List<String>,
        imageNames: List<String>,
        thumbnail: String,
        title: String,
        desc: String,
        prices: List<PostDetails.Prices>,
        personPrice: Long,
        studio: Boolean,
    ): PostDetails {
        val response = postNetworkDataSource.createPost(
            postRequest = PostRequest(
                vibes = vibes,
                locations = locations,
                imageNames = imageNames,
                thumbnail = thumbnail,
                title = title,
                desc = desc,
                prices = listOf(
                    PostRequest.PriceRequest(
                        min = prices[0].min,
                        price = prices[0].price,
                    ),
                ),
                personPrice = personPrice,
                studio = studio,
            )
        )
        return with(response) {
            PostDetails(
                id = id,
                maker = PostDetails.Maker(
                    id = maker.id,
                    nickname = maker.nickname,
                ),
                title = title,
                thumbnail = thumbnail,
                images = images,
                vibes = vibes,
                locations = locations,
                studio = studio,
                like = like,
                createdAt = createdAt,
                prices = prices.map {
                    PostDetails.Prices(
                        min = it.min,
                        price = it.price,
                    )
                },
                personPrice = response.personPrice,
                desc = desc,
            )
        }
    }

    override suspend fun likePost(postId: String) {
        postNetworkDataSource.likePost(postId)
    }

    override suspend fun unlikePost(postId: String) {
        postNetworkDataSource.unlikePost(postId)
    }

    override suspend fun getPostLikeCount(postId: String): Int {
        val response = postNetworkDataSource.getPostLikeCount(postId)
        return response.count
    }

    override suspend fun getAllPosts(): PostList {
        val response = postNetworkDataSource.getAllPosts()
        return with(response) {
            PostList(
                offset = offset,
                limit = limit,
                data = response.data.map {
                    PostList.Post(
                        id = it.id,
                        maker = PostList.Post.Maker(
                            id = it.maker.id,
                            nickname = it.maker.nickname,
                        ),
                        title = it.title,
                        thumbnail = it.thumbnail,
                        vibes = it.vibes,
                        locations = it.locations,
                        price = it.price,
                        studio = it.studio,
                        like = it.like,
                    )
                },
            )
        }
    }

    override suspend fun getPostsByVibes(vibes: List<String>): PostList {
        val response = postNetworkDataSource.getPostsByVibes(vibes)
        return with(response) {
            PostList(
                offset = offset,
                limit = limit,
                data = data.map {
                    PostList.Post(
                        id = it.id,
                        maker = PostList.Post.Maker(
                            id = it.maker.id,
                            nickname = it.maker.nickname,
                        ),
                        title = it.title,
                        thumbnail = it.thumbnail,
                        vibes = it.vibes,
                        locations = it.locations,
                        price = it.price,
                        studio = it.studio,
                        like = it.like,
                    )
                },
            )
        }
    }
}
