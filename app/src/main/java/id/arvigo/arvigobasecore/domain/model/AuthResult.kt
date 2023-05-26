package id.arvigo.arvigobasecore.domain.model

import id.arvigo.arvigobasecore.util.Resource

data class AuthResult(
    val passwordError: String? = null,
    val emailError : String? = null,
    val result: Resource<Unit>? = null
)