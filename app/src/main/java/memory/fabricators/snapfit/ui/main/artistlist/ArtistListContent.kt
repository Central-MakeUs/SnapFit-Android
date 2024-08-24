package memory.fabricators.snapfit.ui.main.artistlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistListContent(
    modifier: Modifier = Modifier,
    viewModel: ArtistListViewModel = koinViewModel(),
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
        ) {
            val (selectedTab, setSelectedTab) = remember { mutableIntStateOf(0) }
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
            /*Row(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(
                        top = 8.dp,
                        end = 16.dp,
                        bottom = 8.dp,
                    ),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    tint = LocalColorScheme.current.primaryBlack,
                    painter = painterResource(id = R.drawable.icon_menu),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                )
                Text(
                    text = "필터",
                    style = LocalTypography.current.body2Regular.copy(
                        color = LocalColorScheme.current.primaryBlack,
                    ),
                )
            }
            HorizontalDivider(
                thickness = 5.dp,
                color = LocalColorScheme.current.secondary100,
            )*/
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 2),
        ) {

        }
    }
}
