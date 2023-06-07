package id.arvigo.arvigobasecore.ui.navigation


const val ROOT_GRAPH_ROUTE = "root"
const val AUTH_GRAPH_ROUTE = "auth"
const val HOME_GRAPH_ROUTE = "home"
const val PERSONALITY_GRAPH_ROUTE = "personality"
const val PRODUCT_ID = "productId"
const val BRAND_ID = "brandId"
const val BRAND_LOGO = "brandLogo"
const val BRAND_NAME = "brandName"
const val FACESHAPE_RESULT_IMAGE = "faceshapeResultImage"
const val FACESHAPE_RESULT = "faceshapeResult"

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
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
    object Search : Screen("search")
    object Store : Screen("store")
    object StoreDetail : Screen("store_detail")

    object Makeup : Screen("makeup")
    object PersonalRecomendation : Screen("personal_recomendation")
    object ProductDetail : Screen("product_detail/{$PRODUCT_ID}") {
        fun createRoute(productId: Int) = "product_detail/$productId"
    }
    object RecommendationStore : Screen("recommendation_store/{$PRODUCT_ID}") {
        fun createRoute(productId: String) = "recommendation_store/$productId"
    }
    object BrandDetail : Screen("brand_detail/{$BRAND_ID}/{$BRAND_LOGO}/{$BRAND_NAME}") {
        //fun createRoute(brandId: Int) = "brand_detail/$brandId"
        fun passData(brandId: Int, brandLogo: String, brandName: String) = "brand_detail/$brandId/$brandLogo/$brandName"
    }
    object FaceshapeRecommendation : Screen("faceshape_recommendation/{$FACESHAPE_RESULT}/{$FACESHAPE_RESULT_IMAGE}") {
        fun passData(result: String, resultImage: String) = "faceshape_recommendation/$result/$resultImage"
    }
    object FaceGuide : Screen("face_guide")
}
