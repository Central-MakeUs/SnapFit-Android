package memory.fabricators.snapfit.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import memory.fabricators.snapfit.data.post.PostRepository
import memory.fabricators.snapfit.data.post.model.PostList
import memory.fabricators.snapfit.data.user.UserRepository
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class HomeViewModel(
    private val userRepository: UserRepository,
    private val postRepository: PostRepository,
) : ViewModel(), ContainerHost<HomeUiState, Unit> {
    override val container = container<HomeUiState, Unit>(HomeUiState())

    init {
        fetchPosts()
    }

    fun fetchPosts(
        limit: Int = 20,
        offset: Int = 1,
    ) = intent {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                postRepository.getAllPosts()
            }.onSuccess {
                reduce {
                    state.copy(posts = it.data)
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}

data class HomeUiState(
    val posts: List<PostList.Post>? = null,
)
