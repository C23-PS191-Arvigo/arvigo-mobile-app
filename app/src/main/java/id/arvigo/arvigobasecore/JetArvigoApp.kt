package id.arvigo.arvigobasecore

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import id.arvigo.arvigobasecore.ui.feature.brand.BrandScreen
import id.arvigo.arvigobasecore.ui.feature.eyewear.EyewearScreen
import id.arvigo.arvigobasecore.ui.feature.faceshape.FaceShapeIntroScreen
import id.arvigo.arvigobasecore.ui.feature.faceshape.FaceShapePhotoScreen
import id.arvigo.arvigobasecore.ui.feature.home.HomeScreen
import id.arvigo.arvigobasecore.ui.feature.login.LoginScreen
import id.arvigo.arvigobasecore.ui.feature.personality.PersonalityMainTestScreen
import id.arvigo.arvigobasecore.ui.feature.personality.PersonalityResultScreen
import id.arvigo.arvigobasecore.ui.feature.personality.PersonalityScreen
import id.arvigo.arvigobasecore.ui.feature.profile.PricingScreen
import id.arvigo.arvigobasecore.ui.feature.profile.ProfileEditScreen
import id.arvigo.arvigobasecore.ui.feature.profile.ProfileScreen
import id.arvigo.arvigobasecore.ui.feature.register.RegisterScreen
import id.arvigo.arvigobasecore.ui.feature.splash.SplashScreen
import id.arvigo.arvigobasecore.ui.feature.wishlist.WishListScreen
import id.arvigo.arvigobasecore.ui.navigation.*
import id.arvigo.arvigobasecore.ui.navigation.nav_graph.authNavGraph

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
                Screen.Login.route,
                Screen.Register.route,
                Screen.Brand.route,
                Screen.Eyewear.route,
                Screen.Splash.route,
                Screen.FaceShapeIntro.route,
                Screen.FaceShapePhoto.route,
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
            composable(Screen.Home.route) {
                HomeScreen(
                    navController = navController,
                )
            }
            composable(Screen.Wishlist.route) {
                WishListScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen(
                    navController = navController
                )
            }
            composable(Screen.Brand.route) {
                BrandScreen(
                    navController = navController,
                )
            }
            //auth
            composable(Screen.Login.route) {
                LoginScreen(
                    navController = navController,
                )
            }
            composable(Screen.Register.route) {
                RegisterScreen()
            }

            // Personality
            composable(Screen.Personality.route) {
                PersonalityScreen(
                    navController = navController,
                )
            }
            composable(Screen.PersonalityMainTest.route) {
                PersonalityMainTestScreen(
                    navController = navController,
                )
            }
            composable(Screen.PersonalityResult.route) {
                PersonalityResultScreen()
            }
            //Profile
            composable(Screen.ProfileEdit.route){
                ProfileEditScreen(navController)
            }
            composable(Screen.Pricing.route){
                PricingScreen()
            }
            //category
            composable(Screen.Eyewear.route){
                EyewearScreen(
                    navController = navController,
                )
            }
            //face-shape
            composable(Screen.FaceShapeIntro.route){
                FaceShapeIntroScreen(
                    navController = navController,
                )
            }
            composable(Screen.FaceShapePhoto.route) {
                FaceShapePhotoScreen()
            }
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