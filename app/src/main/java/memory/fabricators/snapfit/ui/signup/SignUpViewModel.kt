package memory.fabricators.snapfit.ui.signup

import androidx.lifecycle.ViewModel
import memory.fabricators.snapfit.data.auth.AuthRepository
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class SignUpViewModel(
    val authRepository: AuthRepository,
) : ViewModel(),
    ContainerHost<SignUpState, SignUpSideEffect> {
    override val container = container<SignUpState, SignUpSideEffect>(
        SignUpState.initial(),
    )

    fun updateNickname(value: String) = intent {
        reduce {
            state.copy(nickname = value)
        }
    }
}

data class SignUpState(
    val nickname: String,

    ) {
    companion object {
        fun initial(): SignUpState = SignUpState(
            nickname = "",
        )
    }
}

sealed class SignUpSideEffect {

}
