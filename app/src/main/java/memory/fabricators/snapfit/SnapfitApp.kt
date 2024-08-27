package memory.fabricators.snapfit

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import memory.fabricators.snapfit.ui.main.MainScreen
import memory.fabricators.snapfit.ui.post.booking.BookingScreen
import memory.fabricators.snapfit.ui.post.details.PostDetailsScreen
import memory.fabricators.snapfit.ui.post.listfilter.ArtistListFilterScreen
import memory.fabricators.snapfit.ui.post.result.BookingCompletionScreen
import memory.fabricators.snapfit.ui.signup.SignUpScreen
import memory.fabricators.snapfit.ui.start.StartScreen

@Composable
fun SnapfitApp() {
    val navController = rememberNavController()
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = SnapfitDestinations.START.route,
    ) {
        composable(route = SnapfitDestinations.MAIN.route) {
            MainScreen(
                onOpenPostDetails = { postId ->
                    navController.navigate(
                        route = SnapfitDestinations.POST_DETAILS.route + "?postId=$postId",
                    ) {
                        launchSingleTop = true
                    }
                },
            )
        }
        composable(route = SnapfitDestinations.START.route) {
            StartScreen(
                onOpenSignUp = { token ->
                    navController.navigate(
                        route = SnapfitDestinations.SIGN_UP.route + "?socialAccessToken=$token",
                    ) {
                        launchSingleTop = true
                    }
                },
                onOpenMain = {
                    navController.navigate(
                        route = SnapfitDestinations.MAIN.route,
                    ) {
                        launchSingleTop = true
                    }
                },
            )
        }
        composable(
            route = SnapfitDestinations.SIGN_UP.route + "?socialAccessToken={socialAccessToken}",
            arguments = listOf(
                navArgument("socialAccessToken") {
                    type = NavType.StringType
                },
            )
        ) { backStackEntry ->
            val socialAccessToken = backStackEntry.arguments?.getString("socialAccessToken")
                ?: throw IllegalArgumentException("Social access token not received")
            SignUpScreen(
                onNavigateUp = navController::navigateUp,
                onOpenMain = {
                    navController.navigate(
                        route = SnapfitDestinations.MAIN.route,
                    ) {
                        launchSingleTop = true
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                },
                socialAccessToken = socialAccessToken,
            )
        }
        composable(
            route = SnapfitDestinations.POST_DETAILS.route + "?postId={postId}",
            arguments = listOf(
                navArgument("postId") { type = NavType.LongType },
            ),
        ) { backStackEntry ->
            val postId = backStackEntry.arguments?.getLong("postId")
                ?: throw RuntimeException("Post ID not found.")
            PostDetailsScreen(
                postId = postId,
                onNavigateUp = navController::navigateUp,
                onNavigateToBooking = {
                    navController.navigate(
                        route = SnapfitDestinations.BOOKING_SCREEN.route + "?postId=$postId",
                    ) {
                        launchSingleTop = true
                    }
                },
            )
        }
        composable(route = SnapfitDestinations.BOOKING_COMPLETION.route) {
            BookingCompletionScreen()
        }
        composable(route = SnapfitDestinations.ARTIST_LIST_FILTER.route) {
            ArtistListFilterScreen()
        }
        composable(
            route = SnapfitDestinations.BOOKING_SCREEN.route + "?postId={postId}",
            arguments = listOf(
                navArgument("postId") { type = NavType.LongType },
            ),
        ) { backStackEntry ->
            val postId = backStackEntry.arguments?.getLong("postId")
                ?: throw RuntimeException("Post ID not provided.")
            BookingScreen(
                onNavigateUp = navController::navigateUp,
                onOpenReservationResult = {
                    navController.navigate(SnapfitDestinations.BOOKING_COMPLETION.route) {
                        launchSingleTop = true
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                },
                postId = postId,
            )
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
    POST_DETAILS(
        route = "post_details",
    ),
    BOOKING_COMPLETION(
        route = "booking_completion",
    ),
    ARTIST_LIST_FILTER(
        route = "artist_list_filter",
    ),
    BOOKING_SCREEN(
        route = "booking",
    )
}
