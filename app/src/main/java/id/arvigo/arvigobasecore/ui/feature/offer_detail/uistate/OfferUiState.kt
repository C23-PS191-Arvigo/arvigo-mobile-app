package id.arvigo.arvigobasecore.ui.feature.offer_detail.uistate

import id.arvigo.arvigobasecore.data.source.network.response.offer.OfferItem
import id.arvigo.arvigobasecore.data.source.network.response.product_detail.ProductDetailData
import id.arvigo.arvigobasecore.ui.feature.product_detail.uistate.ProductDetailUiState

sealed class OfferUiState {

    class Success(val data: OfferItem) : OfferUiState()
    class Failure(val error: Throwable) : OfferUiState()
    object Loading : OfferUiState()
    object Empty : OfferUiState()

}