package memory.fabricators.snapfit.ui.main.artistlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import memory.fabricators.snapfit.data.post.PostRepository
import memory.fabricators.snapfit.data.post.model.PostList
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ArtistListViewModel(
    private val postRepository: PostRepository,
) : ViewModel(), ContainerHost<ArtistListState, Unit> {
    override val container = container<ArtistListState, Unit>(ArtistListState())

    init {
        fetchAllPosts()
    }

    fun fetchAllPosts() = intent {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                postRepository.getAllPosts()
            }.onSuccess {
                reduce {
                    state.copy(allPosts = it.data)
                }
            }
        }
    }

    fun fetchLovelyPosts() = intent {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                postRepository.getPostsByVibes(vibes = listOf("러블리"))
            }.onSuccess {
                reduce {
                    state.copy(lovelyPosts = it.data)
                }
            }
        }
    }

    fun fetchChicPosts() = intent {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                postRepository.getPostsByVibes(vibes = listOf("시크"))
            }.onSuccess {
                reduce {
                    state.copy(chicPosts = it.data)
                }
            }
        }
    }

    fun kitschPosts() = intent {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                postRepository.getPostsByVibes(vibes = listOf("키치"))
            }.onSuccess {
                reduce {
                    state.copy(kitschPosts = it.data)
                }
            }
        }
    }

    fun calmPosts() = intent {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                postRepository.getPostsByVibes(vibes = listOf("차분한"))
            }.onSuccess {
                reduce {
                    state.copy(calmPosts = it.data)
                }
            }
        }
    }
}

data class ArtistListState(
    val allPosts: List<PostList.Post>? = null,
    val lovelyPosts: List<PostList.Post>? = null,
    val chicPosts: List<PostList.Post>? = null,
    val kitschPosts: List<PostList.Post>? = null,
    val calmPosts: List<PostList.Post>? = null,
)
