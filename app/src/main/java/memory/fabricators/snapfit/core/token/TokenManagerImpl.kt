package memory.fabricators.snapfit.core.token

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TokenManagerImpl(
    private val httpClient: HttpClient,
    private val dataStore: DataStore<Preferences>,
) : TokenManager() {

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
        CoroutineScope(Dispatchers.IO).launch {
            dataStore.edit {
                it[KEY_REFRESH_TOKEN] = refreshToken
            }
        }
        _cachedAccessToken = accessToken
        _cachedRefreshToken = refreshToken
    }

    override fun initialize() {

    }
}

private val KEY_REFRESH_TOKEN = stringPreferencesKey("refresh_token")
