package id.arvigo.arvigobasecore.ui.navigation.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import id.arvigo.arvigobasecore.ui.feature.login.LoginScreen
import id.arvigo.arvigobasecore.ui.feature.register.RegisterScreen
import id.arvigo.arvigobasecore.ui.navigation.AUTH_GRAPH_ROUTE
import id.arvigo.arvigobasecore.ui.navigation.Screen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Screen.Login.route,
        route = AUTH_GRAPH_ROUTE
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
               navController = navController,
            )
        }
        composable(Screen.Register.route) {
            RegisterScreen()
        }
    }
}