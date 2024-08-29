package memory.fabricators.snapfit.ui.reservation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import memory.fabricators.snapfit.R
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.LocalTypography
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservationListScreen(
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ReservationListViewModel = koinViewModel(),
) {
    val state by viewModel.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "예약 내역") },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateUp,
                    ) {
                        Icon(
                            tint = LocalColorScheme.current.primaryBlack,
                            painter = painterResource(id = R.drawable.icon_arrow_left),
                            contentDescription = "back",
                        )
                    }
                },
            )
        },
    ) { innerPaddings ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings)
                .imePadding(),
        ) {
            if (state.reservationList != null)
                items(state.reservationList!!) { item ->
                    ReservationItem(
                        thumbnail = item.post.thumbNail,
                        title = item.post.title,
                        locations = item.post.locations,
                        reservationTime = item.reservationTime,
                        price = item.totalPrice,
                        onClick = { /*TODO*/ },
                        modifier = Modifier.fillMaxWidth(),
                    )
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = LocalColorScheme.current.secondary100,
                    )
                }
        }
    }
}

@Composable
private fun ReservationItem(
    thumbnail: String,
    title: String,
    locations: List<String>,
    reservationTime: String,
    price: Long,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clip(
                shape = RoundedCornerShape(2.dp),
            )
            .clickable(
                onClick = onClick,
            )
            .padding(
                all = 16.dp,
            ),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = thumbnail,
            contentDescription = null,
            modifier = Modifier
                .size(130.dp)
                .clip(
                    shape = RoundedCornerShape(5.dp),
                ),
            contentScale = ContentScale.Crop,
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = "$title|${locations.joinToString()}",
                style = LocalTypography.current.body2Semibold,
                color = LocalColorScheme.current.secondary500,
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "예약일시",
                    style = LocalTypography.current.caption1Semibold,
                    color = LocalColorScheme.current.secondary500,
                )
                Text(
                    text = reservationTime,
                    style = LocalTypography.current.caption2Regular,
                    color = LocalColorScheme.current.secondary500,
                )
            }

            Text(
                text = "${price}원",
                style = LocalTypography.current.body1Semibold,
                color = LocalColorScheme.current.secondary500,
            )
        }
    }
}
