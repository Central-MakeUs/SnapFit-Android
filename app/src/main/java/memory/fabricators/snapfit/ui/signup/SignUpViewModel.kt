package memory.fabricators.snapfit.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import memory.fabricators.snapfit.data.user.UserRepository
import memory.fabricators.snapfit.data.user.model.Vibe
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
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

    fun signUp(
        selectedVibes: List<String>,
        socialAccessToken: String,
        nickname: String,
    ) = intent {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                userRepository.signUp(
                    social = "kakao",
                    socialAccessToken = socialAccessToken,
                    vibes = selectedVibes,
                    deviceType = "android",
                    deviceToken = "",
                    nickname = nickname,
                    marketing = true,
                )
            }.onSuccess {
                postSideEffect(SignUpSideEffect.SignUpSuccess)
            }.onFailure {
                postSideEffect(SignUpSideEffect.SignUpFailure)
            }
        }
    }
}

data class SignUpState(
    val vibes: List<Vibe>? = null,
)

sealed class SignUpSideEffect {
    data object SignUpSuccess : SignUpSideEffect()
    data object SignUpFailure : SignUpSideEffect()
}
