package id.arvigo.arvigobasecore.ui.navigation.nav_graph

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import id.arvigo.arvigobasecore.data.source.network.response.stores.StoreDataItem
import id.arvigo.arvigobasecore.ui.feature.brand.BrandScreen
import id.arvigo.arvigobasecore.ui.feature.brand.brand_detail.BrandDetailScreen
import id.arvigo.arvigobasecore.ui.feature.eyewear.EyewearScreen
import id.arvigo.arvigobasecore.ui.feature.faceshape.FaceShapeIntroScreen
import id.arvigo.arvigobasecore.ui.feature.faceshape.FaceShapePhotoScreen
import id.arvigo.arvigobasecore.ui.feature.faceshape.recommendation.FaceGuideScreen
import id.arvigo.arvigobasecore.ui.feature.faceshape.recommendation.FaceshapeRecommendation
import id.arvigo.arvigobasecore.ui.feature.home.HomeScreen
import id.arvigo.arvigobasecore.ui.feature.makeup.MakeupScreen
import id.arvigo.arvigobasecore.ui.feature.notification.NotificationScreen
import id.arvigo.arvigobasecore.ui.feature.offer_detail.OfferScreen
import id.arvigo.arvigobasecore.ui.feature.personality.PersonalityMainTestScreen
import id.arvigo.arvigobasecore.ui.feature.personality.PersonalityResultScreen
import id.arvigo.arvigobasecore.ui.feature.personality.PersonalityScreen
import id.arvigo.arvigobasecore.ui.feature.personality.recommendation.PersonalRecomenScreen
import id.arvigo.arvigobasecore.ui.feature.product_detail.ProductDetailScreen
import id.arvigo.arvigobasecore.ui.feature.profile.ProfileScreen
import id.arvigo.arvigobasecore.ui.feature.subscription.PaymentScreen
import id.arvigo.arvigobasecore.ui.feature.subscription.PricingScreen
import id.arvigo.arvigobasecore.ui.feature.profile.screen.ProfileEditScreen
import id.arvigo.arvigobasecore.ui.feature.recommendation_store.RecommenStoreScreen
import id.arvigo.arvigobasecore.ui.feature.search.SearchScreen
import id.arvigo.arvigobasecore.ui.feature.stores.StoreScreen
import id.arvigo.arvigobasecore.ui.feature.stores.store_detail.StoreDetail
import id.arvigo.arvigobasecore.ui.feature.wishlist.WishListScreen
import id.arvigo.arvigobasecore.ui.navigation.BRAND_ID
import id.arvigo.arvigobasecore.ui.navigation.BRAND_LOGO
import id.arvigo.arvigobasecore.ui.navigation.BRAND_NAME
import id.arvigo.arvigobasecore.ui.navigation.FACESHAPE_RESULT
import id.arvigo.arvigobasecore.ui.navigation.FACESHAPE_RESULT_IMAGE
import id.arvigo.arvigobasecore.ui.navigation.PERSONALITY_RESULT
import id.arvigo.arvigobasecore.ui.navigation.PRODUCT_ID
import id.arvigo.arvigobasecore.ui.navigation.Screen

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController
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
        ProfileScreen(navController = navController)
    }
    //Profile
    composable(Screen.ProfileEdit.route) {
        ProfileEditScreen(navController)
    }
    composable(Screen.Pricing.route) {
        PricingScreen(navController = navController)
    }
    composable(Screen.Payment.route) {
        PaymentScreen(navController = navController)
    }
    //category
    composable(Screen.Eyewear.route) {
        EyewearScreen(
            navController = navController,
        )
    }
    composable(Screen.Makeup.route) {
        MakeupScreen(
            navController = navController,
        )
    }
    //face-shape
    composable(Screen.FaceShapeIntro.route) {
        FaceShapeIntroScreen(
            navController = navController,
        )
    }
    composable(
        route = Screen.FaceShapePhoto.route
    ) {
        FaceShapePhotoScreen(
            navController = navController,
        )
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
        PersonalityResultScreen(
            navController = navController,
        )
    }
    composable(
        route = Screen.PersonalRecommendation.route,
        arguments = listOf(
            navArgument(PERSONALITY_RESULT) {
                type = NavType.StringType
            }
        )
    ) {
        val result = it.arguments?.getString(PERSONALITY_RESULT)
        PersonalRecomenScreen(
            navController = navController,
            personalResult = result ?: "",
        )
    }
    composable(
        route = Screen.FaceShapeRecommendation.route,
        arguments = listOf(
            navArgument(FACESHAPE_RESULT) {
                type = NavType.StringType
            },
            navArgument(FACESHAPE_RESULT_IMAGE) {
                type = NavType.StringType
            }
        )
    ){
        val result = it.arguments?.getString(FACESHAPE_RESULT)
        val resultImage = it.arguments?.getString(FACESHAPE_RESULT_IMAGE)
        FaceshapeRecommendation(
            navController = navController,
            result = result ?: "",
            resultImage = resultImage ?: ""
        )
    }
    composable(Screen.FaceGuide.route){
        FaceGuideScreen(
            navController = navController,
        )
    }
    //search
    composable(Screen.Search.route){
        SearchScreen(
            navController = navController,
        )
    }
    //store
    composable(Screen.Store.route){
        StoreScreen(
            navController = navController,
        )
    }
    composable(route = Screen.StoreDetail.route){
        val result = navController.previousBackStackEntry?.savedStateHandle?.get<List<StoreDataItem>>("stores")
        Log.d("Args", result.toString())
        StoreDetail(
            navController = navController,
            storeDataItem = result ?: emptyList()
        )
    }
    //product detail
    composable(
        route = Screen.ProductDetail.route,
        arguments = listOf(
            navArgument(PRODUCT_ID) {
                type = NavType.IntType
            }
        )
    ) {
        Log.d("Args", it.arguments?.getInt(PRODUCT_ID).toString())
        val productId = it.arguments?.getInt(PRODUCT_ID).toString()
        ProductDetailScreen(
            navController = navController,
            productId = productId,
        )
    }
    composable(
        route = Screen.RecommendationStore.route,
        arguments = listOf(
            navArgument(PRODUCT_ID) {
                type = NavType.StringType
            }
        )
    ) {
        val productId = it.arguments?.getString(PRODUCT_ID)!!
        RecommenStoreScreen(
            navController = navController,
            productId = productId,
        )
    }
    composable(
        route = Screen.BrandDetail.route,
        arguments = listOf(
            navArgument(BRAND_ID) {
                type = NavType.IntType
            },
            navArgument(BRAND_LOGO) {
                type = NavType.StringType
            },
            navArgument(BRAND_NAME) {
                type = NavType.StringType
            }
        )
    ) {
        val brandId = it.arguments?.getInt(BRAND_ID).toString()
        val brandLogo = it.arguments?.getString(BRAND_LOGO).toString()
        val brandName = it.arguments?.getString(BRAND_NAME).toString()
        BrandDetailScreen(
            navController = navController,
            brandId = brandId,
            brandLogo = brandLogo,
            brandName = brandName,
        )
    }
    //notification
    composable(Screen.Notification.route){
        NotificationScreen(
            navController = navController,
        )
    }
    //offer detail
    composable(
        route = Screen.OfferDetail.route,
        arguments = listOf(
            navArgument(PRODUCT_ID) {
                type = NavType.IntType
            }
        )
    ) {
        Log.d("Args", it.arguments?.getInt(PRODUCT_ID).toString())
        val productId = it.arguments?.getInt(PRODUCT_ID).toString()
        OfferScreen(
            navController = navController,
            productId = productId,
        )
    }
    composable(Screen.Brand.route) {
        BrandScreen(
            navController = navController,
        )
    }
}
