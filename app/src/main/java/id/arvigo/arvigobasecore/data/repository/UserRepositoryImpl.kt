package id.arvigo.arvigobasecore.data.repository

import id.arvigo.arvigobasecore.data.source.network.UserDataSource
import id.arvigo.arvigobasecore.data.source.network.request.LoginRequest
import id.arvigo.arvigobasecore.data.source.network.response.LoginResponse
import id.arvigo.arvigobasecore.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

//class UserRepositoryImpl(private val userDataSource: UserDataSource) : UserRepository {
//    override fun login(request: LoginRequest): LoginResponse {
//       return userDataSource.login(request = request)
//    }
//}