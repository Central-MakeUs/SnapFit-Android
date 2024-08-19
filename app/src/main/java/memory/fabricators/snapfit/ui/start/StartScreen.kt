package memory.fabricators.snapfit.ui.start

import android.widget.Toast
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
import androidx.compose.runtime.remember
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
import memory.fabricators.snapfit.R
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.LocalTypography
import memory.fabricators.snapfit.core.kakao.kakaoTalkLoginAvailable
import memory.fabricators.snapfit.core.kakao.loginWithKakaoAccount
import memory.fabricators.snapfit.core.kakao.loginWithKakaoTalk
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun StartScreen(
    onOpenSignUp: (token: String) -> Unit,
    onOpenMain: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: StartViewModel = koinViewModel(),
) {
    val context = LocalContext.current
    val onKakaoLoginSuccess = remember {
        { token: OAuthToken ->
            viewModel.login(token.accessToken)
            Unit
        }
    }
    val onKakaoLoginFailure = remember {
        { error: Throwable ->
            Toast.makeText(
                context,
                context.getString(
                    R.string.start_login_failure,
                    error.localizedMessage,
                ),
                Toast.LENGTH_SHORT,
            ).show()
        }
    }
    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is StartSideEffect.LoginFailure -> onOpenSignUp(sideEffect.socialAccessToken)
            StartSideEffect.LoginSuccess -> onOpenMain()
        }
    }

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
                AuthButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        if (context.kakaoTalkLoginAvailable) {
                            context.loginWithKakaoTalk(
                                onSuccess = onKakaoLoginSuccess,
                                onFailure = onKakaoLoginFailure,
                            )
                        } else {
                            context.loginWithKakaoAccount(
                                onSuccess = onKakaoLoginSuccess,
                                onFailure = onKakaoLoginFailure,
                            )
                        }
                    },
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
                        text = stringResource(id = R.string.start_login_kakao),
                    )
                }

                /*AuthButton(
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
