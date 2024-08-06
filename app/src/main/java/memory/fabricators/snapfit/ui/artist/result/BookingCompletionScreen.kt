package memory.fabricators.snapfit.ui.artist.result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import memory.fabricators.snapfit.core.design_system.Button
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.LocalTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingCompletionScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { /*TODO*/ },
                navigationIcon = {
                    IconButton(
                        onClick = { /*TODO*/ },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "close",
                        )
                    }
                },
            )
        },
    ) { innerPaddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings)
                .verticalScroll(rememberScrollState()),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 32.dp,
                    ),
            ) {
                // TODO
                Text(
                    text = "예약이 접수되었습니다.",
                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                    ),
                    style = LocalTypography.current.title1Semibold,
                    color = LocalColorScheme.current.secondary500,
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "적어주신 연락처로\n작가가 연락드릴 예정입니다.",
                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                    ),
                    style = LocalTypography.current.body1Regular,
                    color = LocalColorScheme.current.secondary400,
                )
            }
            HorizontalDivider(
                thickness = 5.dp,
                color = LocalColorScheme.current.secondary100,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 32.dp,
                    ),
            ) {
                // TODO
                Text(
                    text = "주문상품",
                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                    ),
                    style = LocalTypography.current.subtitle1Semibold,
                    color = LocalColorScheme.current.secondary500,
                )
                Spacer(modifier = Modifier.height(32.dp))
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .height(130.dp)
                        .background(color = Color.Green),
                )
            }
            HorizontalDivider(
                thickness = 5.dp,
                color = LocalColorScheme.current.secondary100,
            )
            // TODO
            val dummyOptions = mapOf(
                "옵션" to "30분 스냅",
                "위치" to "센트럴파크",
                "예약일시" to "24.02.22(목) 오후 5:00",
                "인원" to "성인 1명",
                "이메일" to "snap@naver.com",
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 32.dp,
                    ),
                verticalArrangement = Arrangement.spacedBy(24.dp),
            ) {
                dummyOptions.forEach { option ->
                    BookingDescription(
                        title = { Text(text = option.key) },
                        description = { Text(text = option.value) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                    )
                }
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 16.dp,
                    ),
            ) {
                Text(text = "예약내역 보러가기")
            }
        }
    }
}

@Composable
private fun BookingDescription(
    title: @Composable () -> Unit,
    description: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ProvideTextStyle(
            value = LocalTypography.current.body2Regular.copy(
                color = LocalColorScheme.current.secondary400,
            ),
            content = title,
        )

        ProvideTextStyle(
            value = LocalTypography.current.body2Semibold.copy(
                color = LocalColorScheme.current.secondary500,
            ),
            content = description,
        )
    }
}
