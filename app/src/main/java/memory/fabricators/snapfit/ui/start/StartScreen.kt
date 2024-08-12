package memory.fabricators.snapfit.ui.start

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
                AuthButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {/* TODO */ },
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
                        text = stringResource(id = R.string.start_loginWithKakao),
                    )
                }
                /*AuthButton(
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
