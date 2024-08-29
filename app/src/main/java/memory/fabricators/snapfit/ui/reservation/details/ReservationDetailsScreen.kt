package memory.fabricators.snapfit.ui.reservation.details

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.LocalTypography
import memory.fabricators.snapfit.core.design_system.SectionHeader
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun ReservationDetailsScreen(
    bookingId: Long,
    modifier: Modifier = Modifier,
    viewModel: ReservationDetailsViewModel = koinViewModel(),
) {
    val state by viewModel.collectAsState()
    LaunchedEffect(key1 = bookingId) {
        viewModel.fetchReservationDetails(bookingId)
    }

    Scaffold(
        modifier = modifier,
    ) { innerPaddings ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(innerPaddings),
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            SectionHeader(
                title = { Text(text = "주문 상품") },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(16.dp))
            AnimatedVisibility(
                visible = state.reservationDetails != null,
            ) {
                val reservationDetails = state.reservationDetails!!
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(32.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AsyncImage(
                        model = reservationDetails.post.thumbNail,
                        contentDescription = reservationDetails.post.title,
                        modifier = Modifier
                            .size(
                                size = 130.dp,
                            )
                            .clip(
                                shape = RoundedCornerShape(5.dp),
                            ),
                        contentScale = ContentScale.Crop,
                    )
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Text(
                            text = reservationDetails.post.title,
                            style = LocalTypography.current.body2Semibold,
                            color = LocalColorScheme.current.secondary500,
                        )
                        Text(
                            text = reservationDetails.post.locations.joinToString(),
                            style = LocalTypography.current.caption2Regular,
                            color = LocalColorScheme.current.secondary500,
                        )
                        Text(
                            text = reservationDetails.personPrice.toString(),
                            style = LocalTypography.current.caption1Semibold,
                            color = LocalColorScheme.current.secondary500,
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            reservationDetails.post.vibes.forEach { vibe ->
                                MemoryRecommendationTag {
                                    Text(text = vibe)
                                }
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            HorizontalDivider(
                thickness = 5.dp,
                color = LocalColorScheme.current.secondary100,
            )
            Spacer(modifier = Modifier.height(32.dp))
            SectionHeader(
                title = { Text(text = "예약 내역") },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun MemoryRecommendationTag(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .background(
                color = LocalColorScheme.current.accentPink.copy(
                    alpha = 0.1f,
                ),
                shape = RoundedCornerShape(2.dp),
            )
            .padding(
                horizontal = 8.dp,
                vertical = 2.dp,
            ),
    ) {
        ProvideTextStyle(
            value = LocalTypography.current.caption1Semibold.copy(
                color = LocalColorScheme.current.accentPink,
            ),
            content = content,
        )
    }
}
