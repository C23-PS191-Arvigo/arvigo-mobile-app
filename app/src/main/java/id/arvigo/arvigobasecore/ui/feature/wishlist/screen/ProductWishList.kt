package id.arvigo.arvigobasecore.ui.feature.wishlist.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import id.arvigo.arvigobasecore.ui.component.lazy.ProductLazyGridTwo
import id.arvigo.arvigobasecore.ui.feature.wishlist.ProductsUiState
import id.arvigo.arvigobasecore.ui.feature.wishlist.model.WishListViewModel
import id.arvigo.arvigobasecore.ui.theme.ArvigoBaseCoreTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun ProductWishlistScreen(
    navController: NavController
) {
    val viewModel: WishListViewModel = getViewModel()

    when(val response = viewModel.response.value){
        is ProductsUiState.Success -> {
            ProductLazyGridTwo(itemList = response.data, navController = navController)
        }
        is ProductsUiState.Failure -> {
            Text(text = response.error.message ?: "Unknown Error")
        }
        is ProductsUiState.Empty -> {}
        is ProductsUiState.Loading -> {
            // TODO 1: Add Loading state
            Box(modifier = Modifier.fillMaxSize()){
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductWishlistPreview() {
/*    ArvigoBaseCoreTheme {
        ProductWishlistScreen()
    }*/
}
