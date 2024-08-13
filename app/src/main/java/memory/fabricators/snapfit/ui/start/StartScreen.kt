package memory.fabricators.snapfit.ui.start

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import memory.fabricators.snapfit.R
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.LocalTypography

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        containerColor = LocalColorScheme.current.primaryBlack,
        contentColor = LocalColorScheme.current.primaryWhite,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Spacer(modifier = Modifier.height(128.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(24.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.app_logo_extended),
                    contentDescription = null,
                    modifier = Modifier.padding(start = 16.dp),
                    tint = Color.Unspecified,
                )
                Text(
                    text = stringResource(R.string.start_subtitle),
                    modifier = Modifier.padding(start = 16.dp),
                    style = LocalTypography.current.body1Regular,
                    color = LocalColorScheme.current.secondary400,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                val context = LocalContext.current
                AuthButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { createKakaoToken(context) },
                    colors = AuthButtonDefaults.kakaoColors(),
                    leadingIcon = {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(id = R.drawable.ic_kakao),
                            contentDescription = null,
                        )
                    },
                ) {
                    Text(
                        text = stringResource(id = R.string.start_loginWithKakao),
                    )
                }/*AuthButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                    },
                    colors = AuthButtonDefaults.appleColors(),
                    leadingIcon = {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.ic_apple),
                            contentDescription = null,
                        )
                    },
                ) {
                    Text(
                        text = stringResource(id = R.string.start_loginWithApple),
                    )
                }*/
            }
        }
    }
}

@Composable
private fun AuthButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    colors: AuthButtonColors,
    leadingIcon: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalContentColor provides colors.contentColor,
    ) {
        ProvideTextStyle(
            value = LocalTypography.current.body2Semibold.copy(
                color = colors.contentColor,
            ),
        ) {
            Box(
                modifier = modifier
                    .semantics {
                        role = Role.Button
                    }
                    .clickable(
                        onClick = onClick,
                    )
                    .background(
                        color = colors.containerColor,
                        shape = RoundedCornerShape(5.dp),
                    )
                    .clip(
                        shape = RoundedCornerShape(5.dp),
                    )
                    .heightIn(
                        min = 50.dp,
                    ),
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(
                            start = 32.dp,
                            top = 16.dp,
                            bottom = 16.dp,
                        ),
                ) {
                    leadingIcon()
                }
                Box(
                    modifier = Modifier.align(Alignment.Center),
                    content = { content() },
                )
            }
        }
    }
}

private class AuthButtonColors(
    val contentColor: Color,
    val containerColor: Color,
)

private object AuthButtonDefaults {
    fun kakaoColors(): AuthButtonColors = AuthButtonColors(
        contentColor = Color(0xFF3C1E1E),
        containerColor = Color(0xFFFEDE35),
    )

    fun appleColors(): AuthButtonColors = AuthButtonColors(
        contentColor = Color(0xFF000000),
        containerColor = Color(0xFFFFFFFF),
    )
}

fun createKakaoToken(
    context: Context,
) {
    // 로그인 조합 예제
    // 카카오계정으로 로그인 공통 callback 구성
    // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
    val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            // _loginApiState.value = ApiState.Error("카카오계정으로 로그인 실패")
        } else if (token != null) {
            // 로그인성공에대한로직()
            println("하하하")
        }
    }
    // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
    if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
        UserApiClient.instance.loginWithKakaoTalk(context = context) { token, error ->
            if (error != null) {
                // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                if (error is com.kakao.sdk.common.model.ClientError && error.reason == ClientErrorCause.Cancelled) {
                    return@loginWithKakaoTalk
                }
                // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                UserApiClient.instance.loginWithKakaoAccount(context = context, callback = callback)
            } else if (token != null) {
                //   로그인성공에대한로직()
                println("호호호")
            }
        }
    } else {
        UserApiClient.instance.loginWithKakaoAccount(context = context, callback = callback)
    }
}