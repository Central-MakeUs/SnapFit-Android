package memory.fabricators.snapfit.ui.post.booking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import memory.fabricators.snapfit.data.post.PostRepository
import memory.fabricators.snapfit.data.post.model.PostDetails
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class BookingViewModel(
    private val postRepository: PostRepository,
) : ViewModel(), ContainerHost<BookingState, Unit> {
    override val container = container<BookingState, Unit>(BookingState())

    fun fetchPostDetails(postId: Long) = intent {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                postRepository.getPostDetails(postId)
            }.onSuccess {
                reduce {
                    state.copy(postDetails = it)
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}

data class BookingState(
    val postDetails: PostDetails? = null,
)
