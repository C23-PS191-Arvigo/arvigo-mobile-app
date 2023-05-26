package id.arvigo.arvigobasecore.data.source.network

import id.arvigo.arvigobasecore.data.source.network.request.LoginRequest
import id.arvigo.arvigobasecore.data.source.network.response.LoginResponse
import id.arvigo.arvigobasecore.ui.feature.register.model.RegisterRequest
import id.arvigo.arvigobasecore.ui.feature.register.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.POST

interface ApiService {
    @POST("/v1/auth/register-user")
    fun register(
        @Body request: RegisterRequest
    ): Call<RegisterResponse>

    @POST("/v1/auth/login")
    fun login(
        @Body request: LoginRequest
    ): LoginResponse
}