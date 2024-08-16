package memory.fabricators.snapfit.core.kakao

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient

val Context.kakaoTalkLoginAvailable: Boolean
    get() = UserApiClient.instance.isKakaoTalkLoginAvailable(context = this)

fun Context.loginWithKakaoTalk(
    onSuccess: (token: OAuthToken) -> Unit,
    onFailure: (error: Throwable) -> Unit,
) = UserApiClient.instance.loginWithKakaoTalk(
    context = this,
    callback = { token, error ->
        if (token != null) {
            onSuccess(token)
        }
        if (error != null) {
            onFailure(error)
        }
    },
)

fun Context.loginWithKakaoAccount(
    onSuccess: (token: OAuthToken) -> Unit,
    onFailure: (error: Throwable) -> Unit,
) = UserApiClient.instance.loginWithKakaoAccount(
    context = this,
    callback = { token, error ->
        if (token != null) {
            onSuccess(token)
        }
        if (error != null) {
            onFailure(error)
        }
    },
)
