package memory.fabricators.snapfit.ui.main.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import memory.fabricators.snapfit.data.reservation.ReservationRepository
import memory.fabricators.snapfit.data.user.UserRepository
import memory.fabricators.snapfit.data.user.model.UserInfo
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class MyPageViewModel(
    private val userRepository: UserRepository,
    private val reservationRepository: ReservationRepository,
) : ViewModel(),
    ContainerHost<MyPageState, Unit> {
    override val container = container<MyPageState, Unit>(MyPageState())

    init {
        fetchUserInfo()
        fetchReservationCount()
        fetchFavoriteCount()
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

    private fun fetchReservationCount() = intent {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                reservationRepository.getReservationCount()
            }.onSuccess {
                reduce {
                    state.copy(reservationCount = it)
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    private fun fetchFavoriteCount() = intent {
        kotlin.runCatching {
            reservationRepository.getReservationCount()
        }.onSuccess {
            reduce {
                state.copy(favoriteCount = it)
            }
        }.onFailure {
            it.printStackTrace()
        }
    }
}

data class MyPageState(
    val userInfo: UserInfo? = null,
    val reservationCount: Int? = null,
    val favoriteCount: Int? = null,
)
