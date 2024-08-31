package memory.fabricators.snapfit.ui.main.artistlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import memory.fabricators.snapfit.R
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.LocalTypography
import memory.fabricators.snapfit.core.number.decFormat
import memory.fabricators.snapfit.ui.common.ProductItem
import memory.fabricators.snapfit.ui.common.ProductItemTag
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistListContent(
    onOpenPostDetails: (postId: Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ArtistListViewModel = koinViewModel(),
) {
    val state by viewModel.collectAsState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val (selectedTab, setSelectedTab) = remember { mutableIntStateOf(0) }

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
                ),
        ) {
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = LocalColorScheme.current.primaryWhite,
                contentColor = LocalColorScheme.current.primaryBlack,
                indicator = @Composable { tabPositions ->
                    if (selectedTab < tabPositions.size) {
                        TabRowDefaults.SecondaryIndicator(
                            modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                            color = LocalColorScheme.current.primaryBlack,
                        )
                    }
                },
            ) {
                CompositionLocalProvider(
                    value = LocalContentColor provides LocalColorScheme.current.primaryBlack,
                ) {
                    Tab(
                        selected = true,
                        onClick = { setSelectedTab(0) },
                    ) {
                        Text(
                            text = "전체",
                            modifier = Modifier.padding(vertical = 16.dp),
                            style = LocalTypography.current.body2Semibold,
                        )
                    }

                    Tab(
                        selected = true,
                        onClick = { setSelectedTab(1) },
                    ) {
                        Text(
                            text = "러블리",
                            modifier = Modifier.padding(vertical = 16.dp),
                            style = LocalTypography.current.body2Semibold,
                        )
                    }

                    Tab(
                        selected = true,
                        onClick = { setSelectedTab(2) },
                    ) {
                        Text(
                            text = "시크",
                            modifier = Modifier.padding(vertical = 16.dp),
                            style = LocalTypography.current.body2Semibold,
                        )
                    }

                    Tab(
                        selected = true,
                        onClick = { setSelectedTab(3) },
                    ) {
                        Text(
                            text = "키치",
                            modifier = Modifier.padding(vertical = 16.dp),
                            style = LocalTypography.current.body2Semibold,
                        )
                    }

                    Tab(
                        selected = true,
                        onClick = { setSelectedTab(4) },
                    ) {
                        Text(
                            text = "차분함",
                            modifier = Modifier.padding(vertical = 16.dp),
                            style = LocalTypography.current.body2Semibold,
                        )
                    }
                }
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(count = 2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(all = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(32.dp),
            ) {
                items(
                    when (selectedTab) {
                        0 -> state.allPosts ?: emptyList()
                        1 -> state.lovelyPosts ?: emptyList()
                        2 -> state.chicPosts ?: emptyList()
                        3 -> state.kitschPosts ?: emptyList()
                        4 -> state.calmPosts ?: emptyList()
                        else -> emptyList()
                    },
                ) { post ->
                    ProductItem(
                        backgroundImageUrl = post.thumbnail,
                        title = { Text(text = post.title) },
                        tags = post.vibes.map { ProductItemTag(text = it) },
                        price = { Text(text = "${decFormat.format(post.price)}원") },
                        onClick = { onOpenPostDetails(post.id) },
                        subtitle = { Text(text = post.locations.joinToString { "$it, " }) },
                    )
                }
            }
        }
    }
}
