package memory.fabricators.snapfit.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import memory.fabricators.snapfit.R
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.LocalTypography
import memory.fabricators.snapfit.core.design_system.SectionHeader

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    Scaffold(
        modifier = modifier,
        containerColor = LocalColorScheme.current.primaryWhite,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = LocalColorScheme.current.primaryWhite,
                ),
                title = {
                    Icon(
                        tint = LocalColorScheme.current.secondary500,
                        painter = painterResource(id = R.drawable.app_logo_dark),
                        contentDescription = null,
                    )
                },
                actions = {
                    IconButton(
                        onClick = { /*TODO*/ },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = stringResource(id = R.string.main_notification),
                        )
                    }
                },
            )
        },
    ) { innerPaddings ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPaddings)
                .verticalScroll(scrollState),
            // TODO
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // TODO
            Header(
                username = "한소희",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 32.dp,
                    ),
            )

            // TODO
            SectionHeader(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(
                        text = stringResource(id = R.string.main_sectionTitle_photoSuggestions),
                    )
                },
                action = {
                    IconButton(
                        modifier = Modifier.size(32.dp),
                        onClick = { /*TODO*/ },
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                            contentDescription = stringResource(
                                id = R.string.main_sectionTitle_photoSuggestions,
                            ),
                        )
                    }
                },
            )

            Box(
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp,
                    )
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(
                        color = Color.Red,
                        shape = RoundedCornerShape(2.dp),
                    ),
            )

            // TODO
            val temp = listOf(
                PhotoRecommendation(
                    id = "1234",
                    title = "감성을 담은 스냅사진",
                    location = "서울 중구",
                    price = 36500,
                ),
                PhotoRecommendation(
                    id = "12345",
                    title = "감성을 담은 스냅사진",
                    location = "서울 중구",
                    price = 36500,
                ),
                PhotoRecommendation(
                    id = "12346",
                    title = "감성을 담은 스냅사진",
                    location = "서울 중구",
                    price = 36500,
                ),
                PhotoRecommendation(
                    id = "12347",
                    title = "감성을 담은 스냅사진",
                    location = "서울 중구",
                    price = 36500,
                ),
            )
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(
                    horizontal = 16.dp,
                ),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(
                    items = temp,
                    key = { it.id },
                ) { recommendation ->
                    PhotoRecommendationItem(
                        photoRecommendation = recommendation,
                    )
                }
            }
            // TODO
            SectionHeader(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(
                        text = "메이커와 소중한 추억을 만들어보세요",
                    )
                },
                action = {
                    IconButton(
                        modifier = Modifier.size(32.dp),
                        onClick = { /*TODO*/ },
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                            contentDescription = "메이커와 소중한 추억을 만들어보세요",
                        )
                    }
                },
            )

            val tempRecommendation = listOf(
                MemoryRecommendation(
                    id = "123",
                    title = "추카랜드",
                    tags = listOf(
                        MemoryRecommendation.Tag(
                            id = "1234",
                            title = "시크",
                        ),
                        MemoryRecommendation.Tag(
                            id = "123432",
                            title = "시크",
                        ),
                        MemoryRecommendation.Tag(
                            id = "123413",
                            title = "시크",
                        ),
                    ),
                ),
                MemoryRecommendation(
                    id = "1234",
                    title = "추카랜드",
                    tags = listOf(
                        MemoryRecommendation.Tag(
                            id = "1234",
                            title = "시크",
                        ),
                    ),
                ),
                MemoryRecommendation(
                    id = "1235",
                    title = "추카랜드",
                    tags = listOf(
                        MemoryRecommendation.Tag(
                            id = "12355",
                            title = "시크",
                        ),
                        MemoryRecommendation.Tag(
                            id = "1234",
                            title = "시크",
                        ),
                    ),
                ),

                MemoryRecommendation(
                    id = "12352",
                    title = "추카랜드",
                    tags = listOf(
                        MemoryRecommendation.Tag(
                            id = "12355",
                            title = "시크",
                        ),
                    ),
                ),

                MemoryRecommendation(
                    id = "123522",
                    title = "추카랜드",
                    tags = listOf(
                        MemoryRecommendation.Tag(
                            id = "12355",
                            title = "시크",
                        ),
                        MemoryRecommendation.Tag(
                            id = "12343",
                            title = "시크",
                        ),
                    ),
                ),
            )
            val height = 300 * ((tempRecommendation.size + 1) / 2)
            LazyVerticalGrid(
                modifier = Modifier.height(height.dp),
                columns = GridCells.Fixed(count = 2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp),
                userScrollEnabled = false,
            ) {
                items(
                    items = tempRecommendation,
                    key = { it.id },
                ) { recommendation ->
                    MemoryRecommendationItem(
                        modifier = Modifier.weight(1f),
                        memoryRecommendation = recommendation,
                    )
                }
            }
        }
    }
}

@Composable
private fun Header(
    username: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = stringResource(
                    id = R.string.main_title_helloUser,
                    username,
                ),
                style = LocalTypography.current.title1Semibold.copy(
                    color = LocalColorScheme.current.primaryBlack,
                ),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(
                    id = R.string.main_subtitle_youCanFindPhotoOfMoods,
                ),
                style = LocalTypography.current.body2Regular.copy(
                    color = LocalColorScheme.current.secondary400,
                ),
            )
        }
        // Image(painter = , contentDescription = )
        Box(
            modifier = Modifier
                .size(
                    50.dp,
                )
                .background(
                    color = Color.Red,
                    shape = CircleShape,
                ),
        )
    }
}

data class PhotoRecommendation(
    val id: String,
    val title: String,
    val location: String,
    val price: Long,
    // val imageUrl: String,
)

@Composable
private fun PhotoRecommendationItem(
    photoRecommendation: PhotoRecommendation,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(
                    color = Color.Blue,
                    shape = RoundedCornerShape(2.dp),
                ),
        ) {
            IconButton(
                modifier = Modifier.align(Alignment.TopEnd),
                onClick = { /*TODO*/ },
            ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "favorite",
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = photoRecommendation.title,
            style = LocalTypography.current.body2Semibold,
            color = LocalColorScheme.current.primaryBlack,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = photoRecommendation.location,
            style = LocalTypography.current.caption2Regular,
            color = LocalColorScheme.current.secondary500,
            overflow = TextOverflow.Ellipsis,
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            // TODO
            text = "${photoRecommendation.price}원",
            style = LocalTypography.current.body2Semibold,
            color = LocalColorScheme.current.secondary500,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

data class MemoryRecommendation(
    // val imageUrl: String,
    val id: String,
    val title: String,
    val tags: List<Tag>,
) {
    data class Tag(
        val id: String,
        val title: String,
    )
}

@Composable
private fun MemoryRecommendationItem(
    memoryRecommendation: MemoryRecommendation,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(
                    color = Color.Green,
                    shape = RoundedCornerShape(5.dp),
                ),
        ) {/*
            IconButton(
                modifier = Modifier.align(Alignment.TopEnd),
                onClick = { *//*TODO*//* },
            ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "favorite",
                )
            }*/
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = memoryRecommendation.title,
            style = LocalTypography.current.body2Semibold,
            color = LocalColorScheme.current.primaryBlack,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                items = memoryRecommendation.tags,
                key = { it.id },
            ) { tag ->
                MemoryRecommendationTag {
                    Text(text = tag.title)
                }
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
        OutlinedButton(onClick = { /*TODO*/ }) {

        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen()
}
