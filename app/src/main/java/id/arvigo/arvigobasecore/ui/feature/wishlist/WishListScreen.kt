package id.arvigo.arvigobasecore.ui.feature.wishlist

import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import id.arvigo.arvigobasecore.ui.feature.wishlist.screen.ProductWishlistScreen
import id.arvigo.arvigobasecore.ui.feature.wishlist.screen.StoreWishListScreen
import id.arvigo.arvigobasecore.ui.navigation.Screen
import id.arvigo.arvigobasecore.ui.navigation.TabItems
import id.arvigo.arvigobasecore.ui.theme.ArvigoBaseCoreTheme

@Composable
fun WishListScreen(
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf(
        TabItems(
            title = "Products",
            screen = Screen.ProductWishlist
        ),
        TabItems(
            title = "Stores",
            screen = Screen.StoreWishlist
        )
    )

    // Update the selectedTabIndex when the currentRoute changes
    if (currentRoute != null) {
        selectedTabIndex = tabs.indexOfFirst { it.screen.route == currentRoute }.takeIf { it != -1 } ?: 0
    }

    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val backCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (selectedTabIndex == 1) {
                    selectedTabIndex = 0
                    navController.navigate(Screen.ProductWishlist.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                } else {
                    isEnabled = false
                    backDispatcher?.onBackPressed()
                }
            }
        }
    }

    DisposableEffect(backDispatcher) {
        backCallback.isEnabled = true
        backDispatcher?.addCallback(backCallback)
        onDispose {
            backCallback.remove()
        }
    }
    
    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.background(Color.White)
        ) {
            tabs.forEachIndexed {index, item ->
                Tab(
                    text = { Text(item.title) },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(
                            route = item.screen.route,
                            builder = {
                                selectedTabIndex = index
                                navController.navigate(route = item.screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    restoreState = true
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                )
            }
        }
        NavHost(
            navController = navController,
            startDestination = Screen.ProductWishlist.route,
        ) {
            composable(Screen.StoreWishlist.route){
                StoreWishListScreen()
            }
            composable(Screen.ProductWishlist.route){
                ProductWishlistScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WishListPreview() {
    ArvigoBaseCoreTheme {
        WishListScreen()
    }
}