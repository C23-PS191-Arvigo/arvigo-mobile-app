package id.arvigo.arvigobasecore.domain.usecase

import id.arvigo.arvigobasecore.data.source.network.request.LoginRequest
import id.arvigo.arvigobasecore.data.source.network.response.LoginResponse
import id.arvigo.arvigobasecore.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCase(private val userRepository: UserRepository) {

    operator fun invoke(request: LoginRequest) : LoginResponse {
        return userRepository.login(
           request = request
        )
    }

}