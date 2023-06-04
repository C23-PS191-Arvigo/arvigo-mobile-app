package id.arvigo.arvigobasecore.ui.feature.wishlist.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import id.arvigo.arvigobasecore.ui.component.ProductLazyGrid
import id.arvigo.arvigobasecore.ui.feature.wishlist.ProductsUiState
import id.arvigo.arvigobasecore.ui.feature.wishlist.model.WishListViewModel
import id.arvigo.arvigobasecore.ui.theme.ArvigoBaseCoreTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun ProductWishlistScreen() {
    val viewModel: WishListViewModel = getViewModel()

    when(val response = viewModel.response.value){
        is ProductsUiState.Success -> {
            ProductLazyGrid(itemList = response.data)
        }
        is ProductsUiState.Failure -> {
            Text(text = response.error.message ?: "Unknown Error")
        }
        is ProductsUiState.Empty -> {}
        is ProductsUiState.Loading -> {
            // TODO 1: Add Loading state
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductWishlistPreview() {
    ArvigoBaseCoreTheme {
        ProductWishlistScreen()
    }
}