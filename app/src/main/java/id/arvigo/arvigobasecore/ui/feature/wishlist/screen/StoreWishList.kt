package id.arvigo.arvigobasecore.ui.feature.wishlist.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import id.arvigo.arvigobasecore.ui.component.StoreLazyGrid
import id.arvigo.arvigobasecore.ui.feature.wishlist.StoresUiState
import id.arvigo.arvigobasecore.ui.feature.wishlist.model.WishListViewModel
import id.arvigo.arvigobasecore.ui.theme.ArvigoBaseCoreTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun StoreWishListScreen() {
    val viewModel: WishListViewModel = getViewModel()
    when(val response = viewModel.responseStore.value){
        is StoresUiState.Success -> {
            StoreLazyGrid(itemList = response.data)
        }
        is StoresUiState.Failure -> {
            Text(text = response.error.message ?: "Unknown Error")
        }
        is StoresUiState.Empty -> {}
        is StoresUiState.Loading -> {
            // TODO 1: Add Loading state
        }
    }

}

@Preview(showBackground = true)
@Composable
fun StoreWishlistPreview() {
    ArvigoBaseCoreTheme {
        StoreWishListScreen()
    }
}