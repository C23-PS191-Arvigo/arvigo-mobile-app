package id.arvigo.arvigobasecore.data.repository

import android.util.Log
import id.arvigo.arvigobasecore.data.source.local.AuthPreferences
import id.arvigo.arvigobasecore.data.source.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class OfferDetailRepository(
        private val apiService: ApiService,
        private val authPreferences: AuthPreferences,
) {

    fun getOfferDetail(id: String) = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Offer Product", "Get Offer Detail with id $id")
        emit(apiService.getOfferDetail(
                token = "Bearer $token",
                id = id,
        ).data)
    }.flowOn(Dispatchers.IO)

}