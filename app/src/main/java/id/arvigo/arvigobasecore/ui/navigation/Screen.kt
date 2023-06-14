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
const val PERSONALITY_RESULT = "personalityResult"
const val UNIQUE_CODE = "uniqueCode"

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object Home : Screen("home")
    object Wishlist : Screen("wishlist")
    object Profile : Screen("profile")
    object Login : Screen("login")
    object Register : Screen("register")

    object StoreWishlist : Screen("store_wishlist")
    object ProductWishlist : Screen("product_wishlist")

    object Personality : Screen("personality")
    object PersonalityMainTest : Screen("personality_main_test")
    object PersonalityResult : Screen("personality_result")

    object ProfileEdit : Screen("profile_edit")
    object Pricing : Screen("pricing_screen")
    object Payment : Screen("payment_screen/{$UNIQUE_CODE}"){
        fun createRoute(uniqueCode: Int) = "payment_screen/$uniqueCode"
    }
    object Brand : Screen("brand")
    object Eyewear : Screen("eyewear")
    object FaceShapeIntro : Screen("faceshape_intro")
    object FaceShapePhoto : Screen("faceshape_photo")
    object Search : Screen("search")
    object Store : Screen("store")
    object StoreDetail : Screen("store_detail")

    object Makeup : Screen("makeup")
    object PersonalRecommendation : Screen("personal_recomendation/{$PERSONALITY_RESULT}") {
        fun passData(result: String) = "personal_recomendation/$result"
    }

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
    object FaceShapeRecommendation : Screen("faceshape_recommendation/{$FACESHAPE_RESULT}/{$FACESHAPE_RESULT_IMAGE}") {
        fun passData(result: String, resultImage: String) = "faceshape_recommendation/$result/$resultImage"
    }
    object FaceGuide : Screen("face_guide")
    object Notification : Screen("notification")

    object OfferDetail : Screen("offer_detail/{$PRODUCT_ID}") {
        fun createRoute(productId: Int) = "offer_detail/$productId"
    }
}
