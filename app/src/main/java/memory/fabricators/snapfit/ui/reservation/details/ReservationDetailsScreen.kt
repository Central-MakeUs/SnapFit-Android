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
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import memory.fabricators.snapfit.R
import memory.fabricators.snapfit.core.design_system.BasicDialog
import memory.fabricators.snapfit.core.design_system.Button
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.LocalTypography
import memory.fabricators.snapfit.core.design_system.SectionHeader
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservationDetailsScreen(
    bookingId: Long,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ReservationDetailsViewModel = koinViewModel(),
) {
    val state by viewModel.collectAsState()

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false,
        ),
    )

    val (reason1, onReason1Change) = remember { mutableStateOf(false) }
    val (reason2, onReason2Change) = remember { mutableStateOf(false) }

    fun openBottomSheet() {
        scope.launch {
            scaffoldState.bottomSheetState.expand()
        }
    }

    fun hideBottomSheet() {
        scope.launch {
            scaffoldState.bottomSheetState.hide()
        }
    }

    val (showDialog, onChangeShowDialog) = remember { mutableStateOf(false) }

    fun showCancelDialog() {
        onChangeShowDialog(true)
    }

    fun closeCancelDialog() {
        onChangeShowDialog(false)
    }

    if (showDialog)
        BasicDialog(
            content = {
                Text("예약이 취소되었습니다")
            },
            primaryAction = {
                TextButton(
                    onClick = {
                        closeCancelDialog()
                        onNavigateUp()
                    },
                ) {
                    Text(
                        text = "확인",
                        modifier = Modifier.padding(all = 12.dp),
                    )
                }
            },
            onDismissRequest = {
                closeCancelDialog()
                onNavigateUp()
            },
        )

    LaunchedEffect(key1 = bookingId) {
        viewModel.fetchReservationDetails(bookingId)
    }

    BottomSheetScaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "예약 정보") },
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
        sheetPeekHeight = 0.dp,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        all = 16.dp,
                    ),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "정말로 예약을 취소하실건가요?\n" +
                                "이유를 알려주세요",
                        style = LocalTypography.current.title2Semibold,
                        color = LocalColorScheme.current.primaryBlack,
                    )
                    IconButton(
                        onClick = { hideBottomSheet() },
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_cancel),
                            contentDescription = "close",
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(
                        onClick = { onReason1Change(!reason1) },
                    ) {
                        Icon(
                            painter = painterResource(
                                id = if (reason1) {
                                    R.drawable.icon_checkbox_filled
                                } else {
                                    R.drawable.icon_checkbox_outlined
                                },
                            ),
                            contentDescription = "close",
                        )
                    }
                    Text(
                        text = "사진작가와 연락이 안돼요",
                        style = LocalTypography.current.body2Regular,
                        color = LocalColorScheme.current.secondary500,
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(
                        onClick = { onReason2Change(!reason2) },
                    ) {
                        Icon(
                            painter = painterResource(
                                id = if (reason2) {
                                    R.drawable.icon_checkbox_filled
                                } else {
                                    R.drawable.icon_checkbox_outlined
                                },
                            ),
                            contentDescription = "close",
                        )
                    }
                    Text(
                        text = "잘못 눌렀어요",
                        style = LocalTypography.current.body2Regular,
                        color = LocalColorScheme.current.secondary500,
                    )
                }
                Button(
                    onClick = {
                        hideBottomSheet()
                        showCancelDialog()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 32.dp,
                        ),
                ) {
                    Text(text = "예약 취소")
                }
            }
        },
        scaffoldState = scaffoldState,
        sheetDragHandle = null,
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        all = 16.dp,
                    )
                    .background(
                        color = LocalColorScheme.current.secondary100,
                        shape = RoundedCornerShape(2.dp),
                    )
                    .padding(
                        horizontal = 16.dp,
                        vertical = 32.dp,
                    ),
                contentAlignment = Alignment.Center,
            ) {
                BookingDescription(
                    title = { Text(text = "예약 일시") },
                    description = { Text(text = state.reservationDetails?.reservationTime ?: "-") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            SectionHeader(
                title = { Text(text = "예약 내역") },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(16.dp))
            BookingDescription(
                title = { Text(text = "기본") },
                description = { Text(text = "${state.reservationDetails?.basePrice ?: "-"}원") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )
            Spacer(modifier = Modifier.height(32.dp))
            HorizontalDivider(
                thickness = 1.dp,
                color = LocalColorScheme.current.secondary100,
            )
            Spacer(modifier = Modifier.height(32.dp))
            BookingDescription(
                title = {
                    Text(
                        text = "최종 결재액",
                        style = LocalTypography.current.body1Semibold,
                    )
                },
                description = { Text(text = "${state.reservationDetails?.totalPrice ?: "-"}원") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )
            Spacer(modifier = Modifier.height(48.dp))
            Button(
                onClick = { openBottomSheet() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 16.dp,
                    ),
            ) {
                Text(text = "예약 취소")
            }
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
