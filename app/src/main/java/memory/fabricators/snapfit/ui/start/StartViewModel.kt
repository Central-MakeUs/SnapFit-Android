package memory.fabricators.snapfit.ui.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import memory.fabricators.snapfit.data.login.LoginRepository
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container

class StartViewModel(
    private val loginRepository: LoginRepository,
) : ViewModel(), ContainerHost<Unit, StartSideEffect> {
    override val container = container<Unit, StartSideEffect>(Unit)

    fun login(token: String) = intent {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                loginRepository.login(token)
            }.onSuccess {
                postSideEffect(StartSideEffect.LoginSuccess)
            }.onFailure {
                postSideEffect(StartSideEffect.LoginFailure(socialAccessToken = token))
            }
        }
    } 
}

sealed class StartSideEffect {
    data object LoginSuccess : StartSideEffect()
    class LoginFailure(val socialAccessToken: String) : StartSideEffect()
}
