package id.arvigo.arvigobasecore.data.source.network

import id.arvigo.arvigobasecore.ui.feature.register.model.RegisterRequest
import id.arvigo.arvigobasecore.ui.feature.register.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/v1/auth/register-user")
    fun register(
        @Body request: RegisterRequest
    ): Call<RegisterResponse>
}