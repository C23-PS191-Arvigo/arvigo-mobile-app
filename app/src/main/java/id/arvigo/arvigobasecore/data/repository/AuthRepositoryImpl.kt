package id.arvigo.arvigobasecore.data.repository

import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import id.arvigo.arvigobasecore.data.source.network.ApiService
import id.arvigo.arvigobasecore.data.source.network.request.LoginRequest
import id.arvigo.arvigobasecore.domain.repository.AuthRepository
import id.arvigo.arvigobasecore.util.Resource
import retrofit2.HttpException
import java.io.IOException

class AuthRepositoryImpl(private val apiService: ApiService) : AuthRepository {


    override suspend fun login(loginRequest: LoginRequest): Resource<Unit> {
        return try {
            val response = apiService.login(loginRequest)
            Resource.Success(Unit)
        } catch (e: IOException) {
            Resource.Error(e.message.toString())
        } catch (e: HttpException) {
            Resource.Error(e.message.toString())
        }
    }


}