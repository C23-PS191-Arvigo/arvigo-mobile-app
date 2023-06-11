package id.arvigo.arvigobasecore.ui.feature.offer_detail

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.arvigo.arvigobasecore.data.repository.OfferDetailRepository
import id.arvigo.arvigobasecore.data.repository.WishListsRepository
import id.arvigo.arvigobasecore.data.source.local.AuthPreferences
import id.arvigo.arvigobasecore.data.source.network.ApiService
import id.arvigo.arvigobasecore.data.source.network.request.WishlistStoreRequest
import id.arvigo.arvigobasecore.data.source.network.request.WishlisthProductRequest
import id.arvigo.arvigobasecore.data.source.network.response.wishlist.AddWishlistResponse
import id.arvigo.arvigobasecore.ui.feature.offer_detail.uistate.OfferUiState
import id.arvigo.arvigobasecore.ui.feature.product_detail.uistate.ProductDetailUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OfferViewModel(
        private val offerDetailRepository: OfferDetailRepository,
        private val wishListsRepository: WishListsRepository,
        private val apiService: ApiService,
        private val authPreferences: AuthPreferences,
) : ViewModel() {

    val response: MutableState<OfferUiState> = mutableStateOf(OfferUiState.Empty)

    private val _idState = mutableStateOf("")
    val idState: State<String> = _idState

    private val _isFavorite = mutableStateOf(false)
    val isFavorite: State<Boolean> = _isFavorite

    fun setId(value:String){
        _idState.value = idState.value
    }

    fun getOfferDetail(productId: String) = viewModelScope.launch {
        offerDetailRepository.getOfferDetail(id = productId)
                .onStart {
                    response.value = OfferUiState.Loading
                }.catch {
                    response.value = OfferUiState.Failure(it)
                }.collect {
                    response.value = OfferUiState.Success(it)
                    checkFavoriteStatus(productId)
                    Log.d("DETAIL PRODUCT SUCCESS", "get Product Detail")
                }
    }

    fun addWishlistStore(id : Int) = viewModelScope.launch {
        try {
            val token = authPreferences.getAuthToken()
            val request = WishlistStoreRequest(
                    detailProductMarketplaceId = id,
                    productId = null,
            )
            apiService.addToWishlistStore(
                    token = "Bearer $token",
                    request = request
            ).enqueue(object : Callback<AddWishlistResponse> {
                override fun onResponse(call: Call<AddWishlistResponse>, response: Response<AddWishlistResponse>) {
                    Log.d("ADD WISHLIST SUCCESS", "add wishlist success")
                }

                override fun onFailure(call: Call<AddWishlistResponse>, t: Throwable) {
                    Log.d("ADD WISHLIST FAILED", "add wishlist failed ${t.message}")
                }

            })
        } catch (e: Exception) {
            Log.d("ADD WISHLIST FAILED", "add wishlist failed ${e.message}")
        }

    }

    fun deleteWishlistStore(id : Int) = viewModelScope.launch {
        try {
            val token = authPreferences.getAuthToken()
            val request = WishlistStoreRequest(
                    detailProductMarketplaceId = id,
                    productId = null,
            )
            apiService.deleteToWishlistStore(
                    token = "Bearer $token",
                    request = request
            ).enqueue(object : Callback<AddWishlistResponse> {
                override fun onResponse(call: Call<AddWishlistResponse>, response: Response<AddWishlistResponse>) {
                    Log.d("DELETE WISHLIST SUCCESS", "delete wishlist success")
                }

                override fun onFailure(call: Call<AddWishlistResponse>, t: Throwable) {
                    Log.d("DELETE WISHLIST FAILED", "delete wishlist failed ${t.message}")
                }

            })
        } catch (e: Exception) {
            Log.d("DELETE WISHLIST FAILED", "delete wishlist failed ${e.message}")
        }

    }

    fun checkFavoriteStatus(storeId: String) = viewModelScope.launch {
        val wishListsResponse = wishListsRepository.getWishProducts().firstOrNull()
        val isFavorite = wishListsResponse?.stores?.any { store -> store.id.toString() == storeId }
        _isFavorite.value = isFavorite ?: false
    }

}