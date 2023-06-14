package id.arvigo.arvigobasecore.ui.feature.subscription

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.arvigo.arvigobasecore.data.source.local.AuthPreferences
import id.arvigo.arvigobasecore.data.source.network.ApiService
import id.arvigo.arvigobasecore.ui.feature.subscription.model.SubscriptionRequest
import id.arvigo.arvigobasecore.ui.feature.subscription.model.SubscriptionResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubscriptionViewModel(
    private val apiService: ApiService,
    private val authPreferences: AuthPreferences,
) : ViewModel() {
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading
    private val _responseMessage = MutableStateFlow<String?>(null)
    val responseMessage: StateFlow<String?> = _responseMessage

    fun subscribe(request: SubscriptionRequest){
        viewModelScope.launch {
            _isLoading.value = true
            val token = authPreferences.getAuthToken()
            apiService.addSubscription(token = "Bearer $token",request = request).enqueue(object: Callback<SubscriptionResponse>{
                override fun onResponse(
                    call: Call<SubscriptionResponse>,
                    response: Response<SubscriptionResponse>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful){
                        val body = response.body()
                        if (body!= null){
                            val message = body.message
                            body.data
                            _responseMessage.value = message
                            Log.d("neoTag", "success: $message")
                        } else {
                            _responseMessage.value = "Response body is null"
                            Log.d("neoTag", "Response body is null")
                        }
                    } else {
                        val errorMessage = response.errorBody()?.string()
                        _responseMessage.value = errorMessage
                        Log.d("neoTag", "errorMessage: $errorMessage")
                    }
                }

                override fun onFailure(call: Call<SubscriptionResponse>, t: Throwable) {
                    _isLoading.value = false
                    _responseMessage.value = "Subscription failed: ${t.message}"
                    Log.d("neoTag", "onFailure: $t")
                }
            })
        }
    }

    fun clearResponseMessage() {
        _responseMessage.value = null
    }
}