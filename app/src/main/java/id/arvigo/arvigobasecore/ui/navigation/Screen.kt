package id.arvigo.arvigobasecore.ui.navigation


const val ROOT_GRAPH_ROUTE = "root"
const val AUTH_GRAPH_ROUTE = "auth"
const val HOME_GRAPH_ROUTE = "home"
const val PERSONALITY_GRAPH_ROUTE = "personality"

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Wishlist : Screen("wishlist")
    object Profile : Screen("profile")
    object Login : Screen("login")
    object Register : Screen("register")
    object DetailReward : Screen("home/{rewardId}") {
        fun createRoute(rewardId: Long) = "home/$rewardId"
    }
    object StoreWishlist : Screen("store_wishlist")
    object ProductWishlist : Screen("product_wishlist")

    object Personality : Screen("personality")
    object PersonalityMainTest : Screen("personality_main_test")
    object PersonalityResult : Screen("personality_result")

    object ProfileEdit : Screen("profile_edit")
    object Pricing : Screen("pricing_screen")
    object Brand : Screen("brand")
    object Eyewear : Screen("eyewear")
    object FaceShapeIntro : Screen("faceshape_intro")
    object FaceShapePhoto : Screen("faceshape_photo")
}
