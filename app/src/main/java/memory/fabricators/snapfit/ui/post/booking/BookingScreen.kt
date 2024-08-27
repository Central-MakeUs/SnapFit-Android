package memory.fabricators.snapfit.ui.post.booking

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import memory.fabricators.snapfit.R
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.LocalTypography
import memory.fabricators.snapfit.core.design_system.SectionHeader
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(
    postId: Long,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BookingViewModel = koinViewModel(),
) {
    val state by viewModel.collectAsState()

    val (time, onChangeTime) = remember { mutableStateOf("") }
    val (preferLocation, onChangePreferLocation) = remember { mutableStateOf("") }
    val (preferTime, onChangePreferTime) = remember { mutableStateOf("") }
    val (countOfPeople, onChangeCountOfPeople) = remember { mutableIntStateOf(0) }
    val (email, onChangeEmail) = remember { mutableStateOf("") }
    val (phoneNumber, onChangePhoneNumber) = remember { mutableStateOf("") }

    LaunchedEffect(key1 = postId) {
        viewModel.fetchPostDetails(postId)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "예약") },
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
            ) {
                SectionHeader(
                    title = { Text(text = "주문상품") },
                )
                AnimatedVisibility(
                    visible = state.postDetails != null,
                ) {
                    val postDetails = state.postDetails!!
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        AsyncImage(
                            model = postDetails.thumbnail,
                            contentDescription = postDetails.title,
                            modifier = Modifier
                                .size(
                                    size = 130.dp,
                                )
                                .clip(
                                    shape = RoundedCornerShape(5.dp),
                                ),
                        )
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            Text(
                                text = postDetails.title,
                                style = LocalTypography.current.body2Semibold,
                                color = LocalColorScheme.current.secondary500,
                            )
                            Text(
                                text = postDetails.locations.joinToString(),
                                style = LocalTypography.current.caption2Regular,
                                color = LocalColorScheme.current.secondary500,
                            )
                            Text(
                                text = postDetails.prices.price.toString(),
                                style = LocalTypography.current.caption1Semibold,
                                color = LocalColorScheme.current.secondary500,
                            )
                        }
                    }
                }
            }
        }
    }
}
