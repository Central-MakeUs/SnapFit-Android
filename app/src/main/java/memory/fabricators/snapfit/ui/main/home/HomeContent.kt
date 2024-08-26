package memory.fabricators.snapfit.ui.main.home

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import memory.fabricators.snapfit.R
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.LocalTypography
import memory.fabricators.snapfit.core.design_system.SectionHeader
import memory.fabricators.snapfit.data.post.model.PostList
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    onOpenPostDetails: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
) {
    val state by viewModel.collectAsState()
    val scrollState = rememberScrollState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
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
                        painter = painterResource(id = R.drawable.app_logo_light),
                        contentDescription = null,
                    )
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPaddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = innerPaddings.calculateTopPadding(),
                )
                .nestedScroll(
                    connection = scrollBehavior.nestedScrollConnection,
                )
                .verticalScroll(
                    state = scrollState,
                ),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            AnimatedVisibility(visible = state.userInfo != null) {
                Header(
                    username = state.userInfo!!.nickname,
                    profileImage = state.userInfo!!.profile,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 16.dp,
                            vertical = 32.dp,
                        ),
                )
            }

            // TODO
            SectionHeader(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(
                        text = stringResource(id = R.string.home_section_photoRecommendation),
                    )
                },
                action = {
                    IconButton(
                        modifier = Modifier.size(32.dp),
                        onClick = { /*TODO*/ },
                    ) {
                        Icon(
                            tint = LocalColorScheme.current.primaryBlack,
                            painter = painterResource(id = R.drawable.icon_arrow_right),
                            contentDescription = stringResource(
                                id = R.string.home_section_photoRecommendation,
                            ),
                        )
                    }
                },
            )

            AnimatedVisibility(
                visible = state.posts != null,
            ) {
                val post = state.posts!![0]
                AsyncImage(
                    model = post.thumbnail,
                    contentDescription = post.title,
                    modifier = Modifier
                        .padding(
                            horizontal = 16.dp,
                        )
                        .fillMaxWidth()
                        .height(
                            height = 230.dp,
                        )
                        .clip(
                            shape = RoundedCornerShape(2.dp),
                        ),
                    contentScale = ContentScale.Crop,
                )
            }
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(
                    horizontal = 16.dp,
                ),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                if (state.posts != null) {
                    items(
                        items = state.posts!!,
                        key = { it.id },
                    ) { post ->
                        PhotoRecommendationItem(
                            onOpenPostDetails = onOpenPostDetails,
                            post = post,
                        )
                    }
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
                            tint = LocalColorScheme.current.primaryBlack,
                            painter = painterResource(id = R.drawable.icon_arrow_right),
                            contentDescription = "메이커와 소중한 추억을 만들어보세요",
                        )
                    }
                },
            )
            val height = state.postsByVibe?.let {
                300 * ((it.size + 1) / 2)
            } ?: 0

            LazyVerticalGrid(
                modifier = Modifier.height(height.dp),
                columns = GridCells.Fixed(count = 2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp),
                userScrollEnabled = false,
            ) {
                if (state.postsByVibe != null)
                    items(
                        items = state.postsByVibe!!,
                        key = { it.id },
                    ) { recommendation ->
                        MemoryRecommendationItem(
                            modifier = Modifier.weight(1f),
                            onClick = { },
                            post = recommendation,
                        )
                    }
            }
        }
    }
}

@Composable
private fun Header(
    username: String,
    profileImage: String?,
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
                    id = R.string.home_title_helloUser,
                    username,
                ),
                style = LocalTypography.current.title1Semibold.copy(
                    color = LocalColorScheme.current.primaryBlack,
                ),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(
                    id = R.string.home_title,
                ),
                style = LocalTypography.current.body2Regular.copy(
                    color = LocalColorScheme.current.secondary400,
                ),
            )
        }
        AsyncImage(
            model = profileImage ?: R.drawable.image_profile_placeholder,
            contentDescription = "profile",
            modifier = Modifier
                .size(
                    size = 50.dp,
                )
                .clip(
                    shape = CircleShape,
                ),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.image_profile_placeholder),
        )
    }
}

@Composable
private fun PhotoRecommendationItem(
    onOpenPostDetails: () -> Unit,
    post: PostList.Post,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clip(
                shape = RoundedCornerShape(5.dp),
            )
            .clickable(
                onClick = onOpenPostDetails,
                role = Role.Button,
            ),
    ) {
        Box(
            modifier = Modifier.size(120.dp),
        ) {
            AsyncImage(
                model = post.thumbnail,
                contentDescription = post.title,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(size = 2.dp)),
                contentScale = ContentScale.Crop,
            )
            IconButton(
                modifier = Modifier.align(Alignment.TopEnd),
                onClick = { /*TODO*/ },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_favorite_outlined),
                    contentDescription = "favorite",
                    modifier = Modifier.size(24.dp),
                    tint = LocalColorScheme.current.primaryWhite,
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = post.title,
            modifier = Modifier.widthIn(max = 120.dp),
            maxLines = 1,
            style = LocalTypography.current.body2Semibold,
            color = LocalColorScheme.current.primaryBlack,
            overflow = TextOverflow.Ellipsis,
        )
        Row {
            post.locations.forEach { location ->
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = location,
                    style = LocalTypography.current.caption2Regular,
                    color = LocalColorScheme.current.secondary500,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.width(4.dp))
            }
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            // TODO
            text = "${post.price}원",
            style = LocalTypography.current.body2Semibold,
            color = LocalColorScheme.current.secondary500,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun MemoryRecommendationItem(
    post: PostList.Post,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        AsyncImage(
            model = post.thumbnail,
            contentDescription = post.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(
                    shape = RoundedCornerShape(5.dp),
                ),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = post.title,
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
                items = post.vibes,
                key = { it },
            ) { vibe ->
                MemoryRecommendationTag {
                    Text(text = vibe)
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
    }
}
