package memory.fabricators.snapfit.core.token

class TokenManagerImpl : TokenManager() {

    private lateinit var _cachedAccessToken: AccessToken
    private lateinit var _cachedRefreshToken: RefreshToken

    override val accessToken: AccessToken
        get() = if (::_cachedAccessToken.isInitialized) {
            _cachedAccessToken
        } else {
            throw IllegalStateException()
        }

    override val refreshToken: RefreshToken
        get() = if (::_cachedRefreshToken.isInitialized) {
            _cachedRefreshToken
        } else {
            throw IllegalStateException()
        }

    override fun setTokens(
        accessToken: AccessToken,
        refreshToken: RefreshToken,
    ) {
        _cachedAccessToken = accessToken
        _cachedRefreshToken = refreshToken
    }

    override fun initialize() {
        TODO("Not yet implemented")
    }
}