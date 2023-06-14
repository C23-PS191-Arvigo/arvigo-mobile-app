package id.arvigo.arvigobasecore.ui.feature.subscription.uistate

import id.arvigo.arvigobasecore.data.source.network.response.stores.StoreData

sealed class SubscriptionUiState {
    class Success(val data: List<StoreData>) : SubscriptionUiState()
    class Failure(val error: Throwable) : SubscriptionUiState()
    object Loading : SubscriptionUiState()
    object Empty : SubscriptionUiState()
}