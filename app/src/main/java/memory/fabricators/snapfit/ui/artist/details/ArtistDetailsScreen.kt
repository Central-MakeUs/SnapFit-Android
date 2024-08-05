package memory.fabricators.snapfit.ui.artist.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreHoriz
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import memory.fabricators.snapfit.R
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.LocalTypography

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ArtistDetailsScreen(
    modifier: Modifier = Modifier,
) {
    // TODO
    val images = listOf(
        ArtistImage(
            id = "1",
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR14NETh5SYT4HXp3KvnmgmvB5xNqj0a1oEKw&s",
        ),
        ArtistImage(
            id = "2",
            imageUrl = "https://wallpapers.com/images/hd/best-hd-autumn-leaves-57tbt9tq3xf3vdm2.jpg",
        ),
        ArtistImage(
            id = "3",
            imageUrl = "https://static.vecteezy.com/system/resources/previews/030/355/618/non_2x/flowers-in-the-field-mountains-flowers-nature-nature-hd-wallpaper-ai-generated-free-photo.jpg",
        ),
        ArtistImage(
            id = "4",
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRrlfYAIT-nlIWL5KaDn4ngk1avOoSbitiwow&s",
        ),
    )
    val pagerState = rememberPagerState {
        images.size
    }
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
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "back",
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { /*TODO*/ },
                    ) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "favorite",
                        )
                    }
                    IconButton(
                        onClick = { /*TODO*/ },
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreHoriz,
                            contentDescription = "more",
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
            HorizontalPager(
                modifier = Modifier.fillMaxWidth(),
                state = pagerState,
                key = { images[it].id },
            ) { page ->
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp),
                    // TODO: placeholder = painterResource(id = R.drawable.img_start_background),
                    contentScale = ContentScale.Crop,
                    model = images[page].imageUrl,
                    contentDescription = null,
                )
            }

            // TODO
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 32.dp,
                    ),
            ) {
                val dummyTags = listOf(
                    "시크",
                    "야외스냅",
                    "러블리",
                )
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                        ),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    items(dummyTags) { tag ->
                        Tag {
                            Text(text = tag)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "나의 첫 스냅사진을 감성을 담은 스냅사진",
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
                    // TODO
                    Text(
                        text = "서울 용산구 | 중구",
                        style = LocalTypography.current.body2Regular,
                        color = LocalColorScheme.current.secondary400,
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "32,400원",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    style = LocalTypography.current.title1Semibold,
                    color = LocalColorScheme.current.secondary500,
                )
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
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                        .clip(
                            shape = CircleShape,
                        ),
                )
                // TODO
                Text(
                    text = "추카랜드",
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
                // TODO
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
                    text = "사진에서는 빛과 색감의 조화가 돋보이며, 피사체에 대한 깊이 있는 관찰력이 드러납니다. 사진에서는 빛과 색감의 조화가 돋보이며, 피사체에 대한 깊이 있는 관찰력이 드러납니다.",
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
                val items = listOf(
                    "123",
                    "3215"
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
                    text = "가. 기본 환불 규정\n" +
                            "1. 전문가와 의뢰인의 상호 협의하에 청약 철회 및 환불이 \n" +
                            "   가능합니다.\n" +
                            "2. 섭외, 대여 등 사전 준비 도중 청약 철회 시, 해당 비용을 공제한 \n" +
                            "    금액을 환불 가능합니다.\n" +
                            "3. 촬영 또는 편집 작업 착수 이후 청약 철회 시, 진행된 작업량 \n" +
                            "    또는 작업 일수를 산정한 금액을 공제한 금액을 환불 가능합니다.",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = LocalTypography.current.body2Regular,
                    color = LocalColorScheme.current.secondary400,
                )
            }
        }
    }
}

data class ArtistImage(
    val id: String,
    val imageUrl: String,
)

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
