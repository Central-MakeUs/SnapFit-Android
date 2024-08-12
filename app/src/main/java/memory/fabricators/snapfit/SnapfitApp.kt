package memory.fabricators.snapfit

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import memory.fabricators.snapfit.ui.artist.details.ArtistDetailsScreen
import memory.fabricators.snapfit.ui.artist.listfilter.ArtistListFilterScreen
import memory.fabricators.snapfit.ui.artist.result.BookingCompletionScreen
import memory.fabricators.snapfit.ui.main.MainScreen
import memory.fabricators.snapfit.ui.signup.SignUpScreen
import memory.fabricators.snapfit.ui.start.StartScreen

@Composable
fun SnapfitApp() {
    val navController = rememberNavController()
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = SnapfitDestinations.MAIN.route,
    ) {
        composable(route = SnapfitDestinations.MAIN.route) {
            MainScreen()
        }
        composable(route = SnapfitDestinations.START.route) {
            StartScreen()
        }
        composable(route = SnapfitDestinations.SIGN_UP.route) {
            SignUpScreen(
                onOpenMain = { /*TODO*/ },
            )
        }
        composable(route = SnapfitDestinations.ARTIST_DETAILS.route) {
            ArtistDetailsScreen()
        }
        composable(route = SnapfitDestinations.BOOKING_COMPLETION.route) {
            BookingCompletionScreen()
        }
        composable(route = SnapfitDestinations.ARTIST_LIST_FILTER.route) {
            ArtistListFilterScreen()
        }
    }
}

enum class SnapfitDestinations(
    val route: String,
) {
    START(
        route = "start",
    ),
    SIGN_UP(
        route = "sign_up",
    ),
    MAIN(
        route = "main",
    ),
    ARTIST_DETAILS(
        route = "artist_details",
    ),
    BOOKING_COMPLETION(
        route = "booking_completion",
    ),
    ARTIST_LIST_FILTER(
        route = "artist_list_filter",
    ),
}
