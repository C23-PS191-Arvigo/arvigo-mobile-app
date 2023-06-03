package id.arvigo.arvigobasecore.data.repository

import android.util.Log
import id.arvigo.arvigobasecore.data.source.local.AuthPreferences
import id.arvigo.arvigobasecore.data.source.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SearchProductRepository(
    private val apiService: ApiService,
    private val authPreferences: AuthPreferences,
) {

    fun searchProduct(query: String) = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Search Product", "get Product with query $query")
        emit(apiService.searchProduct(
            token = "Bearer $token",
            search = query,
        ).data)
    }.flowOn(Dispatchers.IO)

}