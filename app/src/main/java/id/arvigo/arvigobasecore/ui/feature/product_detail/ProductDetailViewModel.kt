package id.arvigo.arvigobasecore.ui.feature.product_detail

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.arvigo.arvigobasecore.data.repository.ProductDetailRepository
import id.arvigo.arvigobasecore.data.repository.WishListsRepository
import id.arvigo.arvigobasecore.data.source.network.request.WishlisthProductRequest
import id.arvigo.arvigobasecore.domain.model.TextFieldState
import id.arvigo.arvigobasecore.ui.feature.product_detail.uistate.ProductDetailUiState
import id.arvigo.arvigobasecore.ui.feature.search.uistate.SearchUiState
import id.arvigo.arvigobasecore.ui.feature.wishlist.ProductsUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val productDetailRepository: ProductDetailRepository,
    private val wishListsRepository: WishListsRepository,
) : ViewModel() {

    val response: MutableState<ProductDetailUiState> = mutableStateOf(ProductDetailUiState.Empty)

    private val _idState = mutableStateOf("")
    val idState: State<String> = _idState

    private val _isFavorite = mutableStateOf(false)
    val isFavorite: State<Boolean> = _isFavorite

    fun setId(value:String){
        _idState.value = idState.value
    }

    fun getProductDetail(productId: String) = viewModelScope.launch {
        productDetailRepository.getProductDetail(id = productId)
            .onStart {
                response.value = ProductDetailUiState.Loading
            }.catch {
                response.value = ProductDetailUiState.Failure(it)
            }.collect {
                response.value = ProductDetailUiState.Success(it)
                checkFavoriteStatus(productId)
                Log.d("DETAIL PRODUCT SUCCESS", "get Product Detail")
            }
    }

    fun addWishlistProduct(request: WishlisthProductRequest) = viewModelScope.launch {
        wishListsRepository.addWishlistProduct(request = request)
    }

    fun checkFavoriteStatus(productId: String) = viewModelScope.launch {
        val wishListsResponse = wishListsRepository.getWishProducts().firstOrNull()
        val isFavorite = wishListsResponse?.products?.any { product -> product.id.toString() == productId }
        _isFavorite.value = isFavorite ?: false
    }



}