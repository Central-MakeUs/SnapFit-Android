package memory.fabricators.snapfit.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import memory.fabricators.snapfit.R
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.ui.main.artistlist.ArtistListContent
import memory.fabricators.snapfit.ui.main.home.HomeContent
import memory.fabricators.snapfit.ui.main.mypage.MyPageContent

@Composable
fun MainScreen(
    onOpenPostDetails: (postId: Long) -> Unit,
    onOpenReservationList: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val (currentSection, onCurrentSectionChange) = remember {
        mutableStateOf(MainSections.HOME)
    }
    Scaffold(
        modifier = modifier,
        containerColor = LocalColorScheme.current.primaryWhite,
        contentColor = LocalColorScheme.current.primaryBlack,
        bottomBar = {
            val containerColor = LocalColorScheme.current.primaryWhite
            val contentColor = LocalColorScheme.current.primaryBlack
            val unselectedContentColor = LocalColorScheme.current.secondary300
            Column {
                HorizontalDivider(
                    color = LocalColorScheme.current.secondary100,
                )
                BottomAppBar(
                    containerColor = containerColor,
                    contentColor = contentColor,
                    tonalElevation = 0.dp,
                ) {
                    MainSections.entries.forEach { section ->
                        val selected = section == currentSection
                        NavigationBarItem(
                            selected = selected,
                            onClick = { onCurrentSectionChange(section) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = contentColor,
                                unselectedIconColor = unselectedContentColor,
                                selectedTextColor = contentColor,
                                unselectedTextColor = unselectedContentColor,
                                indicatorColor = Color.Transparent,
                            ),
                            icon = {
                                Icon(
                                    painter = painterResource(id = section.iconRes),
                                    contentDescription = null,
                                )
                            },
                            label = {
                                Text(
                                    text = stringResource(id = section.labelRes),
                                )
                            },
                        )
                    }
                }
            }
        }
    ) { innerPaddings ->
        when (currentSection) {
            MainSections.HOME -> HomeContent(
                onOpenPostDetails = onOpenPostDetails,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom = innerPaddings.calculateBottomPadding(),
                    ),
            )

            MainSections.ARTIST_LIST -> ArtistListContent(
                onOpenPostDetails = onOpenPostDetails,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom = innerPaddings.calculateBottomPadding(),
                    ),
            )

            MainSections.MY_PAGE -> MyPageContent(
                isArtist = false,
                onOpenReservationList = onOpenReservationList,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom = innerPaddings.calculateBottomPadding(),
                    ),
            )
        }
    }
}

enum class MainSections(
    val route: String,
    val labelRes: Int,
    val iconRes: Int,
) {
    HOME(
        route = "home",
        labelRes = R.string.main_bottomNavigation_home,
        iconRes = R.drawable.icon_home,
    ),
    ARTIST_LIST(
        route = "artist_list",
        labelRes = R.string.main_bottomNavigation_artist,
        iconRes = R.drawable.icon_create,
    ),
    MY_PAGE(
        route = "my_page",
        labelRes = R.string.main_bottomNavigation_mypage,
        iconRes = R.drawable.icon_user,
    ),
}
