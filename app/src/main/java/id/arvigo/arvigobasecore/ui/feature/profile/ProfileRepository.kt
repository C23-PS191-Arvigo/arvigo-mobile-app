package id.arvigo.arvigobasecore.ui.feature.profile

import android.util.Log
import id.arvigo.arvigobasecore.data.source.local.AuthPreferences
import id.arvigo.arvigobasecore.data.source.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ProfileRepository(
    private val apiService: ApiService,
    private val authPreferences: AuthPreferences
) {
    fun profileDetails() = flow{
        val token = authPreferences.getAuthToken()
        val userId = authPreferences.getAuthId()
        Log.d("neo-tag", "profileDetails: ${userId.toString()}")

        emit(
            apiService.getProfile(
                token = "Bearer $token",
                userId = userId.toString()
            ).data
        )
    }.flowOn(Dispatchers.IO)
}