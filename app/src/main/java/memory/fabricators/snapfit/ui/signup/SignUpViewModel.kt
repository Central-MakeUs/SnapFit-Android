package memory.fabricators.snapfit.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import memory.fabricators.snapfit.data.user.UserRepository
import memory.fabricators.snapfit.data.user.model.Vibe
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class SignUpViewModel(
    private val userRepository: UserRepository,
) : ViewModel(),
    ContainerHost<SignUpState, SignUpSideEffect> {
    override val container = container<SignUpState, SignUpSideEffect>(
        initialState = SignUpState(),
    )

    init {
        fetchVibes()
    }

    private fun fetchVibes() = intent {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                userRepository.fetchVibes()
            }.onSuccess { fetchedVibes ->
                reduce {
                    state.copy(vibes = fetchedVibes)
                }
            }.onFailure {
                // TODO
            }
        }
    }
}

data class SignUpState(
    val vibes: List<Vibe>? = null,
)

sealed class SignUpSideEffect {

}
