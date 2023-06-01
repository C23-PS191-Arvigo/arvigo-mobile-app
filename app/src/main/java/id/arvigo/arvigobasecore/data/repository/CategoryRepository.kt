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
        Log.d("Hit API Brand", "get Brand")
        emit(apiService.getEyewearCategory(
            token = "Bearer $token"
        ).data)
    }.flowOn(Dispatchers.IO)

}