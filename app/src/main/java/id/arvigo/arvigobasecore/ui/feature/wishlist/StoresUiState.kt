package id.arvigo.arvigobasecore.ui.feature.wishlist

import id.arvigo.arvigobasecore.data.source.network.response.wishlist.WishListsStoreItem

sealed class StoresUiState {
    class Success(val data: List<WishListsStoreItem>) : StoresUiState()
    class Failure(val error: Throwable) : StoresUiState()
    object Loading : StoresUiState()
    object Empty : StoresUiState()
}