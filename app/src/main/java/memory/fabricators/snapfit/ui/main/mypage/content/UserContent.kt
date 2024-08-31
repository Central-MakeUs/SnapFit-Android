package memory.fabricators.snapfit.ui.main.mypage.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import memory.fabricators.snapfit.core.design_system.BasicDialog
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.LocalTypography
import memory.fabricators.snapfit.core.design_system.SectionHeader
import memory.fabricators.snapfit.ui.main.mypage.composables.Setting
import memory.fabricators.snapfit.ui.main.mypage.composables.SettingsList

@Composable
fun UserContent(
    reservationCount: Int?,
    favoriteCount: Int?,
    onOpenReservationList: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val uriHandler = LocalUriHandler.current

    val (showArtistDialog, onShowArtistDialogChange) = remember { mutableStateOf(false) }

    if (showArtistDialog)
        BasicDialog(
            content = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = ("인증된 사용자만 사진작가로 전환할 수 있습니다"),
                        textAlign = TextAlign.Center,
                    )
                }
            },
            primaryAction = {
                Text(
                    text = "확인",
                    modifier = Modifier
                        .clickable { onShowArtistDialogChange(false) }
                        .fillMaxWidth()
                        .padding(all = 16.dp),
                    textAlign = TextAlign.Center,
                )
            },
            onDismissRequest = { onShowArtistDialogChange(false) },
        )

    Column(
        modifier = modifier,
    ) {
        HorizontalDivider(
            color = LocalColorScheme.current.secondary100,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            val localDensity = LocalDensity.current
            var boxHeight by remember { mutableStateOf(0.dp) }
            UserHistoryItem(
                title = { Text(text = "예약 내역") },
                modifier = Modifier
                    .weight(1f)
                    .onGloballyPositioned { coordinates ->
                        boxHeight = with(localDensity) { coordinates.size.height.toDp() }
                    },
            ) {
                Text(text = "${reservationCount ?: "-"}")
            }
            VerticalDivider(
                modifier = Modifier.height(boxHeight),
                color = LocalColorScheme.current.secondary100,
            )

            UserHistoryItem(
                title = { Text(text = "찜 내역") },
                modifier = Modifier
                    .weight(1f)
                    .height(boxHeight),
            ) {
                Text(text = "${favoriteCount ?: "-"}")
            }
        }
        HorizontalDivider(
            color = LocalColorScheme.current.secondary100,
        )
        SectionHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            title = { Text(text = "메이커 관리") },
        )
        SettingsList(
            settings = listOf(
                Setting(
                    title = {
                        Text(text = "사진작가로 전환")
                    },
                    onClick = {
                        onShowArtistDialogChange(true)
                    },
                ),
                Setting(
                    title = {
                        Text(text = "예약관리")
                    },
                    onClick = onOpenReservationList,
                ),
            ),
        )
        SectionHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            title = { Text(text = "스냅핏 설정") },
        )
        SettingsList(
            settings = listOf(
                Setting(
                    title = {
                        Text(text = "고객센터")
                    },
                    onClick = {
                        uriHandler.openUri("https://forms.gle/tMHQQ37FQDW3jrDU8")
                    },
                ),
                Setting(
                    title = {
                        Text(text = "이용약관")
                    },
                    onClick = {
                        uriHandler.openUri("https://mixolydian-beef-6a0.notion.site/04cb97bab76c40d68aa17475c6e53172?pvs=4")
                    },
                ),
                Setting(
                    title = {
                        Text(text = "개인정보처리방침")
                    },
                    onClick = {
                        uriHandler.openUri("https://mixolydian-beef-6a0.notion.site/497ab7ab659743c8b797e2c62e4c7bc9?pvs=4")
                    },
                ),
                Setting(
                    title = {
                        Text(
                            text = "로그아웃",
                            color = LocalColorScheme.current.secondary300,
                        )
                    },
                    onClick = {

                    },
                ),
                Setting(
                    title = {
                        Text(
                            text = "탈퇴하기",
                            color = LocalColorScheme.current.secondary300,
                        )
                    },
                    onClick = {

                    },
                ),
            ),
        )
        Spacer(modifier = Modifier.height(120.dp))
    }
}

@Composable
private fun UserHistoryItem(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier.padding(
            horizontal = 32.dp,
            vertical = 16.dp,
        ),
    ) {
        CompositionLocalProvider(
            value = LocalContentColor provides LocalColorScheme.current.primaryBlack,
        ) {
            ProvideTextStyle(
                value = LocalTypography.current.body1Semibold.copy(
                    color = LocalColorScheme.current.primaryBlack,
                ),
                content = title,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        CompositionLocalProvider(
            value = LocalContentColor provides LocalColorScheme.current.secondary200,
        ) {
            ProvideTextStyle(
                value = LocalTypography.current.title1Semibold.copy(
                    color = LocalColorScheme.current.secondary200,
                ),
                content = content,
            )
        }
    }
}
