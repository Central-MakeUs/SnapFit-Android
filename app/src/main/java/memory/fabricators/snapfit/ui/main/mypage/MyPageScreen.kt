package memory.fabricators.snapfit.ui.main.mypage

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.LocalTypography
import memory.fabricators.snapfit.ui.main.mypage.content.ArtistContent
import memory.fabricators.snapfit.ui.main.mypage.content.UserContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPageScreen(
    isArtist: Boolean,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    Scaffold(
        modifier = modifier,
        containerColor = LocalColorScheme.current.primaryWhite,
        topBar = {
            MyPageTopAppBar(
                username = "한소희",
                tags = listOf(
                    Tag(
                        id = "123",
                        title = "서울 용산구",
                    ),
                    Tag(
                        id = "1234",
                        title = "서울 중구",
                    ),
                ),
                expanded = !scrollState.canScrollBackward,
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
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.PostAdd,
                            contentDescription = "post",
                        )
                    }
                },
            )
        },
    ) { innerPaddings ->
        if (isArtist) {
            ArtistContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = innerPaddings.calculateTopPadding(),
                    )
                    .verticalScroll(scrollState),
            )
        } else {
            UserContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = innerPaddings.calculateTopPadding(),
                    )
                    .verticalScroll(scrollState),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MyPageTopAppBar(
    // backgroundImageUrl: String,
    // profileImageUrl: String,
    username: String,
    tags: List<Tag>,
    expanded: Boolean,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    colors: TopAppBarColors = TopAppBarDefaults.largeTopAppBarColors(),
    scrollBehavior: TopAppBarScrollBehavior? = null,
    pinnedHeight: Dp = MyPageTopAppBarDefaults.PinnedHeight,
    maxHeight: Dp = MyPageTopAppBarDefaults.ContainerHeight,
) {

    val actionsRow = @Composable {
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            content = actions
        )
    }
    Surface(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(windowInsets),
        ) {
            Row(
                modifier = Modifier
                    .background(
                        color = if (expanded) {
                            Color.Transparent
                        } else {
                            colors.containerColor
                        },
                    )
                    .fillMaxWidth()
                    .height(pinnedHeight),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(modifier = Modifier.width(4.dp))
                navigationIcon()
                AnimatedVisibility(
                    visible = !expanded,
                    enter = slideInVertically(initialOffsetY = { it / 2 }) + fadeIn(),
                    exit = slideOutVertically(targetOffsetY = { it / 2 }) + fadeOut(),
                ) {
                    Row(
                        modifier = Modifier.padding(4.dp),
                        content = {
                            Text(
                                text = username,
                                style = LocalTypography.current.title1Semibold,
                                color = LocalColorScheme.current.primaryBlack,
                            )
                        },
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                actionsRow()
            }
            AnimatedVisibility(
                visible = expanded,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    // TODO
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .background(color = Color.Black),
                    )
                    // TODO
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = username,
                            style = LocalTypography.current.title2Regular,
                            color = LocalColorScheme.current.primaryBlack,
                        )
                        // TODO
                        Text(
                            text = "작가",
                            style = LocalTypography.current.title2Semibold,
                            color = LocalColorScheme.current.primaryBlack,
                        )
                    }
                    TagList(tags = tags)
                }
            }
        }
    }
}

@Composable
private fun TagList(
    tags: List<Tag>,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(
            items = tags,
            key = { it.id },
        ) { tag ->
            TagListItem(tag = tag)
        }
    }
}

@Composable
private fun TagListItem(
    tag: Tag,
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(
        value = LocalContentColor provides LocalColorScheme.current.primaryWhite,
    ) {
        Row(
            modifier = modifier
                .background(
                    color = LocalColorScheme.current.primaryBlack,
                    shape = RoundedCornerShape(2.dp),
                )
                .padding(
                    horizontal = 8.dp,
                    vertical = 4.dp,
                ),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
            )
            Text(
                text = tag.title,
                style = LocalTypography.current.caption1Semibold,
                color = LocalColorScheme.current.primaryWhite,
            )
        }
    }
}

private object MyPageTopAppBarDefaults {
    val ContainerHeight = 320.0.dp
    val PinnedHeight = 64.0.dp
}

data class Tag(
    val id: String,
    val title: String,
)

@Preview
@Composable
private fun MyPageScreenPreview() {
    MyPageScreen(
        isArtist = false,
    )
}
