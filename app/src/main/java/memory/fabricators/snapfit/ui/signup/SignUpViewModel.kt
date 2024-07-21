package memory.fabricators.snapfit.ui.signup

import androidx.lifecycle.ViewModel
import memory.fabricators.snapfit.data.auth.AuthRepository
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class SignUpViewModel(
    val authRepository: AuthRepository,
) : ViewModel(),
    ContainerHost<SignUpState, SignUpSideEffect> {
    override val container = container<SignUpState, SignUpSideEffect>(SignUpState())

}

class SignUpState

sealed class SignUpSideEffect {

}
