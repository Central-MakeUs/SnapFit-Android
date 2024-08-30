package memory.fabricators.snapfit

import androidx.lifecycle.ViewModel
import memory.fabricators.snapfit.core.token.TokenManager

class MainActivityViewModel(
    tokenManager: TokenManager,
) : ViewModel() {
    val validTokenExists: Boolean = try {
        tokenManager.refreshToken
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }
}
