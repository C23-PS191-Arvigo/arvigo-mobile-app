package id.arvigo.arvigobasecore.ui.feature.product_detail.uistate

import id.arvigo.arvigobasecore.data.source.network.response.category.CategoryItem
import id.arvigo.arvigobasecore.data.source.network.response.product_detail.ProductDetailData
import id.arvigo.arvigobasecore.ui.feature.search.uistate.SearchUiState

sealed class ProductDetailUiState {
    class Success(val data: ProductDetailData) : ProductDetailUiState()
    class Failure(val error: Throwable) : ProductDetailUiState()
    object Loading : ProductDetailUiState()
    object Empty : ProductDetailUiState()
}
