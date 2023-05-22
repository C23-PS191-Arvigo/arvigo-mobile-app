package id.arvigo.arvigobasecore.data.source.network.request

data class LoginRequest(
    val email: String,
    val password: String,
    val role: String,
)
