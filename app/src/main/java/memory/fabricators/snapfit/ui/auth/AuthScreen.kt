package memory.fabricators.snapfit.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import memory.fabricators.snapfit.R

@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.img_auth_background),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                color = Color.Black.copy(alpha = 0.5f),
                blendMode = BlendMode.Darken,
            ),
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier.padding(start = 16.dp),
                verticalArrangement = Arrangement.spacedBy(32.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.app_logo),
                    tint = Color.White,
                    contentDescription = null,
                )
                Text(
                    text = "나에게 맞는 사진을 만날 수 있는,\n스냅핏에 오신걸 환영합니다!",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                )
            }
            Spacer(modifier = Modifier.weight(4f))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                AuthButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        // TODO
                    },
                    colors = AuthButtonDefaults.kakaoColors(),
                    leadingIcon = {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.ic_kakao),
                            contentDescription = null,
                        )
                    },
                ) {
                    Text(
                        text = stringResource(id = R.string.auth_loginWithKakao),
                    )
                }
                AuthButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        // TODO
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
                        text = stringResource(id = R.string.auth_loginWithApple),
                    )
                }
                TextButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Color(0xFFBDBDBD),
                    ),
                ) {
                    Text(
                        text = stringResource(id = R.string.auth_lookAround),
                        textDecoration = TextDecoration.Underline,
                    )
                }
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
            value = MaterialTheme.typography.bodyMedium.copy(
                color = colors.contentColor,
            ),
        ) {
            Box(
                modifier = modifier
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
                ) {
                    content()
                }
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
