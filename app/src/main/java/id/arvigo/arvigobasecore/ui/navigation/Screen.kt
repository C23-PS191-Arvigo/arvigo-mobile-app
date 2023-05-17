package id.arvigo.arvigobasecore.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Wishlist : Screen("wishlist")
    object Profile : Screen("profile")
    object DetailReward : Screen("home/{rewardId}") {
        fun createRoute(rewardId: Long) = "home/$rewardId"
    }
}
