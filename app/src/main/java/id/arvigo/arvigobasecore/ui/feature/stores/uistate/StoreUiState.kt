package id.arvigo.arvigobasecore.ui.feature.stores.uistate

import id.arvigo.arvigobasecore.data.source.network.response.stores.StoreData

sealed class StoreUiState {
    class Success(val data: List<StoreData>) : StoreUiState()
    class Failure(val error: Throwable) : StoreUiState()
    object Loading : StoreUiState()
    object Empty : StoreUiState()
}
