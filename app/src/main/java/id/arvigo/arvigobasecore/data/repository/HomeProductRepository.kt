package id.arvigo.arvigobasecore.data.repository

import android.util.Log
import id.arvigo.arvigobasecore.data.source.local.AuthPreferences
import id.arvigo.arvigobasecore.data.source.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class HomeProductRepository(
    private val apiService: ApiService,
    private val authPreferences: AuthPreferences,
) {

    fun getHomeProduct() = flow {

        val token = authPreferences.getAuthToken()
        Log.d("Hit API Home Product", "get Home Product")
        emit(apiService.getHomeProduct(
            token = "Bearer $token"
        ).data.recommendations)
    }.flowOn(Dispatchers.IO)

}