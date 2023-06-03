package id.arvigo.arvigobasecore.ui.feature.wishlist.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.arvigo.arvigobasecore.ui.feature.wishlist.ProductsUiState
import id.arvigo.arvigobasecore.data.repository.WishListsRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class WishListViewModel(
    private val wishlistRepo: WishListsRepository
) : ViewModel() {
    val response: MutableState<ProductsUiState> = mutableStateOf(ProductsUiState.Empty)

    init {
        getProductWish()
    }

    private fun getProductWish() = viewModelScope.launch {
        wishlistRepo.getWishProducts()
            .onStart {
                response.value = ProductsUiState.Loading
            }.catch {
                response.value = ProductsUiState.Failure(it)
            }.collect {
                response.value = ProductsUiState.Success(it.products)
            }
    }
}