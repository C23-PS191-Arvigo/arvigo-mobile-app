package id.arvigo.arvigobasecore.ui.feature.wishlist

import id.arvigo.arvigobasecore.data.source.network.response.wishlist.WishListsProductsItem

sealed class ProductsUiState {
    class Success(val data: List<WishListsProductsItem>) : ProductsUiState()
    class Failure(val error: Throwable) : ProductsUiState()
    object Loading : ProductsUiState()
    object Empty : ProductsUiState()
}
