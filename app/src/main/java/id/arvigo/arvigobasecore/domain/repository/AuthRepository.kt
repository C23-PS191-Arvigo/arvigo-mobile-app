package id.arvigo.arvigobasecore.domain.repository

import id.arvigo.arvigobasecore.data.source.network.request.LoginRequest
import id.arvigo.arvigobasecore.util.Resource

interface AuthRepository {
    suspend fun login(loginRequest: LoginRequest): Resource<Unit>
}