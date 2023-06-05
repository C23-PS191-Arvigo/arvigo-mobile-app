package id.arvigo.arvigobasecore.ui.feature.recommendation_store.uistate

import id.arvigo.arvigobasecore.data.source.network.response.product_detail.Marketplace
import id.arvigo.arvigobasecore.data.source.network.response.product_detail.ProductDetailData
import id.arvigo.arvigobasecore.ui.feature.product_detail.uistate.ProductDetailUiState

sealed class RecommenStoreUiState {

    class Success(val data: List<Marketplace>) : RecommenStoreUiState()
    class Failure(val error: Throwable) : RecommenStoreUiState()
    object Loading : RecommenStoreUiState()
    object Empty : RecommenStoreUiState()

}
