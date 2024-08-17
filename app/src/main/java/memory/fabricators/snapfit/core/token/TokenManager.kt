package memory.fabricators.snapfit.core.token

typealias AccessToken = String
typealias RefreshToken = String

abstract class TokenManager {
    abstract val accessToken: AccessToken
    abstract val refreshToken: RefreshToken
    abstract fun setTokens(
        accessToken: AccessToken,
        refreshToken: RefreshToken,
    )

    abstract fun initialize()
}
