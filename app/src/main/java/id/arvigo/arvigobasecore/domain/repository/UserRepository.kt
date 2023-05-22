package id.arvigo.arvigobasecore.domain.repository

import id.arvigo.arvigobasecore.data.source.network.request.LoginRequest
import id.arvigo.arvigobasecore.data.source.network.response.LoginResponse

interface UserRepository {

    fun login(request: LoginRequest) : LoginResponse

}