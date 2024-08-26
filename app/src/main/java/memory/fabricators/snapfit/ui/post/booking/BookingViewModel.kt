package memory.fabricators.snapfit.ui.post.booking

import androidx.lifecycle.ViewModel
import memory.fabricators.snapfit.data.post.model.PostDetails
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class BookingViewModel : ViewModel(),
    ContainerHost<BookingState, Unit> {
    override val container = container<BookingState, Unit>(BookingState())
}

data class BookingState(
    val postDetails: PostDetails? = null,
)
