package id.arvigo.arvigobasecore.ui.feature.product_detail

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.arvigo.arvigobasecore.data.repository.ProductDetailRepository
import id.arvigo.arvigobasecore.domain.model.TextFieldState
import id.arvigo.arvigobasecore.ui.feature.product_detail.uistate.ProductDetailUiState
import id.arvigo.arvigobasecore.ui.feature.search.uistate.SearchUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ProductDetailViewModel(private val productDetailRepository: ProductDetailRepository) : ViewModel() {

    val response: MutableState<ProductDetailUiState> = mutableStateOf(ProductDetailUiState.Empty)

    private val _idState = mutableStateOf(TextFieldState())
    val idState: State<TextFieldState> = _idState

    fun setId(value:String){
        _idState.value = idState.value.copy(text = value)
    }

    init {
        getProductDetail()
    }

    fun getProductDetail() = viewModelScope.launch {
        productDetailRepository.getProductDetail(id = idState.value.text)
            .onStart {
                response.value = ProductDetailUiState.Loading
            }.catch {
                response.value = ProductDetailUiState.Failure(it)
            }.collect {
                response.value = ProductDetailUiState.Success(it)
                Log.d("DETAIL PRODUCT SUCCESS", "get Product Detail")
            }
    }

}