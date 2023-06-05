package id.arvigo.arvigobasecore.ui.feature.recommendation_store

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.arvigo.arvigobasecore.data.repository.ProductDetailRepository
import id.arvigo.arvigobasecore.ui.feature.product_detail.uistate.ProductDetailUiState
import id.arvigo.arvigobasecore.ui.feature.recommendation_store.uistate.RecommenStoreUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class RecommenStoreViewModel(
    private val productDetailRepository: ProductDetailRepository
): ViewModel() {

    val response: MutableState<RecommenStoreUiState> = mutableStateOf(RecommenStoreUiState.Empty)

    fun getProductStore(productId: String) = viewModelScope.launch {
        productDetailRepository.getProductStore(id = productId)
            .onStart {
                response.value = RecommenStoreUiState.Loading
            }.catch {
                response.value = RecommenStoreUiState.Failure(it)
            }.collect {
                response.value = RecommenStoreUiState.Success(it)
                Log.d("DETAIL PRODUCT SUCCESS", "get Product Detail")
            }
    }
}