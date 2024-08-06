package memory.fabricators.snapfit.ui.main.artistlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import memory.fabricators.snapfit.R
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.LocalTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistListContent(
    modifier: Modifier = Modifier,
) {
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
                        painter = painterResource(id = R.drawable.app_logo_dark),
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
                        onClick = { /*TODO*/ },
                    ) {
                        Text(
                            text = "전체",
                            modifier = Modifier.padding(vertical = 16.dp),
                            style = LocalTypography.current.body2Semibold,
                        )
                    }

                    Tab(
                        selected = true,
                        onClick = { /*TODO*/ },
                    ) {
                        Text(
                            text = "러블리",
                            modifier = Modifier.padding(vertical = 16.dp),
                            style = LocalTypography.current.body2Semibold,
                        )
                    }

                    Tab(
                        selected = true,
                        onClick = { /*TODO*/ },
                    ) {
                        Text(
                            text = "시크",
                            modifier = Modifier.padding(vertical = 16.dp),
                            style = LocalTypography.current.body2Semibold,
                        )
                    }

                    Tab(
                        selected = true,
                        onClick = { /*TODO*/ },
                    ) {
                        Text(
                            text = "키치",
                            modifier = Modifier.padding(vertical = 16.dp),
                            style = LocalTypography.current.body2Semibold,
                        )
                    }

                    Tab(
                        selected = true,
                        onClick = { /*TODO*/ },
                    ) {
                        Text(
                            text = "차분함",
                            modifier = Modifier.padding(vertical = 16.dp),
                            style = LocalTypography.current.body2Semibold,
                        )
                    }
                }
            }
            Row(
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
                    imageVector = Icons.Default.FilterList,
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
            )
        }
    }
}
