package memory.fabricators.snapfit.ui.post.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import memory.fabricators.snapfit.R
import memory.fabricators.snapfit.core.design_system.BasicDialog
import memory.fabricators.snapfit.core.design_system.Button
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.LocalTypography
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun PostDetailsScreen(
    postId: Long,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PostDetailsViewModel = koinViewModel(),
) {
    val state by viewModel.collectAsState()
    val (showReportDropdown, onChangeShowReportDropdown) = remember {
        mutableStateOf(false)
    }
    val (showReportCompleteDialog, onChangeShowReportCompleteDialog) = remember {
        mutableStateOf(false)
    }


    if (showReportCompleteDialog) BasicDialog(
        content = { Text(text = "신고가 완료되었습니다") },
        primaryAction = {
            Text(
                text = "확인",
                modifier = Modifier
                    .clickable { onChangeShowReportCompleteDialog(false) }
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                textAlign = TextAlign.Center,
            )
        },
        onDismissRequest = { onChangeShowReportCompleteDialog(false) },
    )

    LaunchedEffect(key1 = postId) {
        viewModel.fetchPostDetails(postId = postId)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { },
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
                actions = {
                    IconButton(
                        onClick = {},
                    ) {
                        Icon(
                            tint = LocalColorScheme.current.primaryBlack,
                            painter = painterResource(id = R.drawable.icon_favorite_outlined),
                            contentDescription = "favorite",
                        )
                    }
                    IconButton(
                        onClick = { onChangeShowReportDropdown(true) },
                    ) {
                        Icon(
                            tint = LocalColorScheme.current.primaryBlack,
                            painter = painterResource(id = R.drawable.icon_more),
                            contentDescription = "more",
                        )
                    }
                    DropdownMenu(
                        expanded = showReportDropdown,
                        onDismissRequest = { onChangeShowReportDropdown(false) },
                    ) {
                        DropdownMenuItem(
                            text = { Text(text = "신고하기") },
                            onClick = {
                                onChangeShowReportDropdown(false)
                                onChangeShowReportCompleteDialog(true)
                            },
                        )
                    }
                },
            )
        },
    ) { innerPaddings ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {
                AnimatedVisibility(
                    visible = state.postDetails != null,
                ) {
                    Column {
                        val postDetails = state.postDetails!!
                        val pagerState = rememberPagerState { postDetails.images.size }
                        HorizontalPager(
                            modifier = Modifier.fillMaxWidth(),
                            state = pagerState,
                            key = { state.postDetails!!.images[it] },
                        ) { page ->
                            val image = postDetails.images[page]
                            AsyncImage(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(350.dp),
                                // TODO: placeholder = painterResource(id = R.drawable.img_start_background),
                                contentScale = ContentScale.Crop,
                                model = image,
                                contentDescription = null,
                            )
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    vertical = 32.dp,
                                ),
                        ) {
                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        start = 16.dp,
                                    ),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                items(postDetails.vibes) { vibe ->
                                    Tag {
                                        Text(text = vibe)
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = postDetails.title,
                                modifier = Modifier.padding(start = 16.dp),
                                style = LocalTypography.current.title2Semibold,
                                color = LocalColorScheme.current.primaryBlack,
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(
                                modifier = Modifier.padding(
                                    start = 16.dp,
                                ),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Icon(
                                    imageVector = Icons.Default.LocationOn,
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp),
                                    tint = LocalColorScheme.current.secondary400,
                                )
                                Text(
                                    text = postDetails.locations.joinToString(separator = " | "),
                                    style = LocalTypography.current.body2Regular,
                                    color = LocalColorScheme.current.secondary400,
                                )
                            }
                            Spacer(modifier = Modifier.height(32.dp))
                            Text(
                                text = postDetails.prices.price.toString(),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                style = LocalTypography.current.title1Semibold,
                                color = LocalColorScheme.current.secondary500,
                            )
                        }
                    }
                }

                HorizontalDivider(
                    thickness = 5.dp,
                    color = LocalColorScheme.current.secondary100,
                )
                Row(
                    modifier = Modifier.padding(all = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AsyncImage(
                        model = state.postDetails?.thumbnail ?: R.drawable.ic_launcher_background,
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .clip(
                                shape = CircleShape,
                            ),
                        contentScale = ContentScale.Crop,
                    )
                    Text(
                        text = state.postDetails?.maker?.nickname ?: "-",
                        style = LocalTypography.current.body1Regular,
                        color = LocalColorScheme.current.primaryBlack,
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
                    Text(
                        text = "작가의 설명",
                        modifier = Modifier.padding(
                            horizontal = 16.dp,
                        ),
                        style = LocalTypography.current.subtitle1Semibold,
                        color = LocalColorScheme.current.secondary500,
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        text = state.postDetails?.desc ?: "-",
                        modifier = Modifier.padding(
                            horizontal = 16.dp,
                        ),
                        style = LocalTypography.current.caption2Regular,
                        color = LocalColorScheme.current.secondary400,
                    )
                }
                HorizontalDivider(
                    thickness = 5.dp,
                    color = LocalColorScheme.current.secondary100,
                )
                Column(
                    modifier = Modifier.padding(vertical = 32.dp),
                ) {
                    // TODO
                    Text(
                        text = "작가의 등록된 상품",
                        modifier = Modifier.padding(start = 16.dp),
                        style = LocalTypography.current.subtitle1Semibold,
                        color = LocalColorScheme.current.primaryBlack,
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    // TODO
                    val items = listOf(
                        "123", "3215"
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 16.dp,
                            ),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        items.forEach { item ->
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(300.dp)
                                    .background(
                                        color = Color.Green,
                                    ),
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    // TODO
                    Text(
                        text = "취소 규정",
                        modifier = Modifier.padding(start = 16.dp),
                        style = LocalTypography.current.subtitle1Semibold,
                        color = LocalColorScheme.current.primaryBlack,
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        text = "가. 기본 환불 규정\n" + "1. 전문가와 의뢰인의 상호 협의하에 청약 철회 및 환불이 \n" + "   가능합니다.\n" + "2. 섭외, 대여 등 사전 준비 도중 청약 철회 시, 해당 비용을 공제한 \n" + "    금액을 환불 가능합니다.\n" + "3. 촬영 또는 편집 작업 착수 이후 청약 철회 시, 진행된 작업량 \n" + "    또는 작업 일수를 산정한 금액을 공제한 금액을 환불 가능합니다.",
                        modifier = Modifier.padding(horizontal = 16.dp),
                        style = LocalTypography.current.body2Regular,
                        color = LocalColorScheme.current.secondary400,
                    )
                }
                Spacer(modifier = Modifier.height(128.dp))
            }
            AnimatedVisibility(
                visible = state.postDetails != null,
                enter = fadeIn(),
            ) {
                Button(
                    onClick = { /*TODO*/
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 16.dp,
                        ),
                ) {
                    Text(text = "예약하기")
                }
            }
        }
    }
}

// TODO: Duplication
@Composable
private fun Tag(
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
