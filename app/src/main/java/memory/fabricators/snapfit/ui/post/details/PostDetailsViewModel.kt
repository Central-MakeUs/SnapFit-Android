package memory.fabricators.snapfit.ui.post.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import memory.fabricators.snapfit.data.post.PostRepository
import memory.fabricators.snapfit.data.post.model.PostDetails
import memory.fabricators.snapfit.data.post.model.PostList
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class PostDetailsViewModel(
    private val postRepository: PostRepository,
) : ViewModel(), ContainerHost<PostDetailsState, Unit> {
    override val container = container<PostDetailsState, Unit>(PostDetailsState())

    fun fetchPostDetails(postId: Long) = intent {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                postRepository.getPostDetails(postId)
            }.onSuccess {
                fetchArtistPost(makerId = it.maker.id)
                reduce {
                    state.copy(postDetails = it)
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    private fun fetchArtistPost(makerId: Long) = intent {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                postRepository.getMakerPosts(
                    makerId = makerId,
                    offset = 0,
                    limit = 2,
                )
            }.onSuccess {
                reduce {
                    state.copy(artistPosts = it.data)
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}

data class PostDetailsState(
    val postDetails: PostDetails? = null,
    val artistPosts: List<PostList.Post>? = null,
)
