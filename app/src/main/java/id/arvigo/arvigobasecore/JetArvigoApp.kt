package id.arvigo.arvigobasecore

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import id.arvigo.arvigobasecore.ui.feature.onboarding.OnboardingScreen
import id.arvigo.arvigobasecore.ui.feature.splash.SplashScreen
import id.arvigo.arvigobasecore.ui.navigation.*
import id.arvigo.arvigobasecore.ui.navigation.nav_graph.authNavGraph
import id.arvigo.arvigobasecore.ui.navigation.nav_graph.homeNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JetArvigoApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            val excludedRoutes = listOf(
                Screen.Personality.route,
                Screen.PersonalityMainTest.route,
                Screen.PersonalityResult.route,
                Screen.Login.route,
                Screen.Register.route,
                Screen.Brand.route,
                Screen.Eyewear.route,
                Screen.Splash.route,
                Screen.FaceShapeIntro.route,
                Screen.FaceShapePhoto.route,
                Screen.Search.route,
                Screen.Onboarding.route,
                Screen.Store.route,
                Screen.Makeup.route,
                Screen.PersonalRecommendation.route,
                Screen.ProductDetail.route,
                Screen.RecommendationStore.route,
                Screen.BrandDetail.route,
                Screen.StoreDetail.route,
                Screen.FaceShapeRecommendation.route,
                Screen.FaceGuide.route,
                Screen.Notification.route,
                Screen.OfferDetail.route,
                Screen.Pricing.route,
                Screen.Payment.route
            )
            if (currentRoute !in excludedRoutes) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(it)
        ) {
            composable(Screen.Splash.route) {
                SplashScreen(
                    navController = navController,
                )
            }
            composable(Screen.Onboarding.route) {
                OnboardingScreen(
                    navController = navController,
                )
            }
            authNavGraph(navController)
            homeNavGraph(navController)
        }
    }
}



@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = "Home",
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = "Wishlist",
                icon = Icons.Default.ShoppingCart,
                screen = Screen.Wishlist
            ),
            NavigationItem(
                title ="Profile",
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            ),
        )
        NavigationBar() {
            navigationItems.map { item ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    label = { Text(item.title) },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}