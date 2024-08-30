package memory.fabricators.snapfit.core.token

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.runBlocking
import memory.fabricators.snapfit.network.login.model.TokenResponse

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

    init {
        try {
            println("INIT")
            this.initialize()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun setTokens(
        accessToken: AccessToken,
        refreshToken: RefreshToken,
    ) {
        runBlocking {
            dataStore.edit {
                it[KEY_REFRESH_TOKEN] = refreshToken
            }
            _cachedAccessToken = accessToken
            _cachedRefreshToken = refreshToken
        }
    }

    override fun initialize() {
        runBlocking {
            dataStore.data.last().also { println() }
            /*
            val refreshToken = dataStore.data.lastOrNull()?.get(KEY_REFRESH_TOKEN)
                ?: throw RuntimeException("Stored Refresh Token not found.")
            val tokens = reissueToken(refreshToken)
            setTokens(
                accessToken = tokens.accessToken,
                refreshToken = tokens.refreshToken,
            )*/
        }
    }

    private suspend fun reissueToken(refreshToken: String): TokenResponse {
        val response = try {
            httpClient.get("/refresh/token") {
                header("refreshToken", refreshToken)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
        return response.body<TokenResponse>().also { println("BODYBODY $it") }
    }
}

private val KEY_REFRESH_TOKEN = stringPreferencesKey("refresh_token")
