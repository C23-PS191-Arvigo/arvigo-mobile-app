package id.arvigo.arvigobasecore.data.repository

import android.util.Log
import id.arvigo.arvigobasecore.data.source.local.AuthPreferences
import id.arvigo.arvigobasecore.data.source.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class BrandRepository(
    private val apiService: ApiService,
    private val authPreferences: AuthPreferences,
) {

    fun getBrand() = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Brand", "get Brand")
        emit(apiService.getBrands(
            token = "Bearer $token"
        ).data)
    }.flowOn(Dispatchers.IO)

    fun getBrandDetail(brandId: String) = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Brand Detail", "get Brand detail with id $brandId")
        emit(apiService.getBrandDetail(
            token = "Bearer $token",
            id = brandId
        ).data)
    }.flowOn(Dispatchers.IO)

}