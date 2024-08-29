package memory.fabricators.snapfit.ui.post.result

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import memory.fabricators.snapfit.core.design_system.Button
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.LocalTypography
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingCompletionScreen(
    bookingId: Long,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BookingCompletionViewModel = koinViewModel(),
) {
    val state by viewModel.collectAsState()

    LaunchedEffect(key1 = bookingId) {
        viewModel.fetchBookingDetails(bookingId)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { },
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
            /*HorizontalDivider(
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
            }*/
            HorizontalDivider(
                thickness = 5.dp,
                color = LocalColorScheme.current.secondary100,
            )
            AnimatedVisibility(
                visible = state.reservationDetails != null,
            ) {
                val reservation = state.reservationDetails!!
                val options = remember(key1 = reservation) {
                    mutableStateMapOf(
                        "옵션" to reservation.post.title,
                        "위치" to reservation.reservationLocation,
                        "에약일시" to reservation.reservationTime,
                        "인원" to "${reservation.person}명",
                        "이메일" to reservation.email,
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 32.dp,
                        ),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                ) {
                    options.forEach { option ->
                        BookingDescription(
                            title = { Text(text = option.key) },
                            description = { Text(text = option.value) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                        )
                    }
                }
            }
            Button(
                onClick = onNavigateUp,
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
