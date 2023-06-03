package id.arvigo.arvigobasecore.data.repository

import id.arvigo.arvigobasecore.data.source.local.AuthPreferences
import id.arvigo.arvigobasecore.data.source.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WishListsRepository(
    private val apiService: ApiService,
    private val authPreferences: AuthPreferences
) {
    fun getWishProducts() = flow {
        val token = authPreferences.getAuthToken()

        emit(apiService.getWishLists(
            token = "Bearer $token"
        ).data)
    }.flowOn(Dispatchers.IO)
}