package memory.fabricators.snapfit.ui.main.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import memory.fabricators.snapfit.data.user.UserRepository
import memory.fabricators.snapfit.data.user.model.UserInfo
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class MyPageViewModel(
    private val userRepository: UserRepository,
) : ViewModel(),
    ContainerHost<MyPageState, Unit> {
    override val container = container<MyPageState, Unit>(MyPageState())

    init {
        fetchUserInfo()
    }

    private fun fetchUserInfo() = intent {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                userRepository.fetchUserInfo()
            }.onSuccess {
                reduce {
                    state.copy(userInfo = it)
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}

data class MyPageState(
    val userInfo: UserInfo? = null,
)
