package id.arvigo.arvigobasecore.domain.usecase

import id.arvigo.arvigobasecore.data.source.network.request.LoginRequest
import id.arvigo.arvigobasecore.data.source.network.response.LoginResponse
import id.arvigo.arvigobasecore.domain.model.AuthResult
import id.arvigo.arvigobasecore.domain.repository.AuthRepository
import id.arvigo.arvigobasecore.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        email:String,
        password:String,
        role:String
    ): AuthResult {

        val emailError = if (email.isBlank()) "Username cannot be blank" else null
        val passwordError = if (password.isBlank()) "Password cannot be blank" else null

        if (emailError != null){
            return AuthResult(
                emailError = emailError
            )
        }

        if (passwordError!=null){
            return AuthResult(
                passwordError = passwordError
            )
        }

        val loginRequest = LoginRequest(
            email = email.trim(),
            password = password.trim(),
            role = role.trim()
        )

        return AuthResult(
            result = repository.login(loginRequest)
        )
    }
}