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

    fun getHomePersonality() = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Home Personality", "get Home Personaltiy")
        emit(apiService.getHomeProduct(
                token = "Bearer $token"
        ).data.personalities)
    }.flowOn(Dispatchers.IO)

    fun getHomeFace() = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Home Face", "get Home Face")
        emit(apiService.getHomeProduct(
                token = "Bearer $token"
        ).data.faceShapes)
    }.flowOn(Dispatchers.IO)

    fun getPersonalFace() = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Home Checking", "get Home Checking")
        emit(apiService.getHomeProduct(
            token = "Bearer $token"
        ).data)
    }.flowOn(Dispatchers.IO)

}