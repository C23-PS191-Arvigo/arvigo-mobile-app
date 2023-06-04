package id.arvigo.arvigobasecore.data.repository

import android.util.Log
import id.arvigo.arvigobasecore.data.source.local.AuthPreferences
import id.arvigo.arvigobasecore.data.source.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CategoryRepository(
    private val apiService: ApiService,
    private val authPreferences: AuthPreferences,
) {

    fun getEyewearCategory() = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Category", "Get Eyewear Category")
        emit(apiService.getEyewearCategory(
            token = "Bearer $token"
        ).data)
    }.flowOn(Dispatchers.IO)

    fun getMakeupCategory() = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Category", "Get Makeup Category")
        emit(apiService.getMakeupCategory(
            token = "Bearer $token"
        ).data)
    }.flowOn(Dispatchers.IO)

}