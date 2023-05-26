package id.arvigo.arvigobasecore.data.source.network

import id.arvigo.arvigobasecore.data.source.network.request.LoginRequest
import id.arvigo.arvigobasecore.data.source.network.response.LoginResponse

interface UserDataSource {
    suspend fun login(request: LoginRequest) : LoginResponse
}