package id.arvigo.arvigobasecore.ui.navigation.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import id.arvigo.arvigobasecore.ui.feature.personality.PersonalityMainTestScreen
import id.arvigo.arvigobasecore.ui.feature.personality.PersonalityResultScreen
import id.arvigo.arvigobasecore.ui.feature.personality.PersonalityScreen
import id.arvigo.arvigobasecore.ui.navigation.PERSONALITY_GRAPH_ROUTE
import id.arvigo.arvigobasecore.ui.navigation.Screen

fun NavGraphBuilder.personalityNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Screen.Personality.route,
        route = PERSONALITY_GRAPH_ROUTE,
    ) {
        composable(Screen.Personality.route) {
            PersonalityScreen(
                navController = navController,
            )
        }
//        navControllercomposable(Screen.PersonalityMainTest.route) {
//            PersonalityMainTestScreen(
//                navController = navController,
//            )
//        }
        composable(Screen.PersonalityResult.route) {
            PersonalityResultScreen(
                navController = navController,
            )
        }
    }
}