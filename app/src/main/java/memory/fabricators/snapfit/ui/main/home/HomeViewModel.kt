package memory.fabricators.snapfit.ui.main.home

import androidx.lifecycle.ViewModel
import memory.fabricators.snapfit.data.user.UserRepository
import org.orbitmvi.orbit.ContainerHost

class HomeViewModel(
    private val userRepository: UserRepository,
) : ViewModel(), ContainerHost<> {
}

data class HomeUiState(
    val
)
