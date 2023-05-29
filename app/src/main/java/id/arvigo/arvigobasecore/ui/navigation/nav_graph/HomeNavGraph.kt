package id.arvigo.arvigobasecore.ui.navigation.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import id.arvigo.arvigobasecore.ui.feature.home.HomeScreen
import id.arvigo.arvigobasecore.ui.feature.profile.ProfileScreen
import id.arvigo.arvigobasecore.ui.feature.wishlist.WishListScreen
import id.arvigo.arvigobasecore.ui.navigation.HOME_GRAPH_ROUTE
import id.arvigo.arvigobasecore.ui.navigation.Screen

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Screen.Home.route,
        route = HOME_GRAPH_ROUTE,
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                navController = navController
            )
        }
        composable(Screen.Wishlist.route) {
            WishListScreen()
        }
        composable(Screen.Profile.route) {
            ProfileScreen()
        }
    }
}