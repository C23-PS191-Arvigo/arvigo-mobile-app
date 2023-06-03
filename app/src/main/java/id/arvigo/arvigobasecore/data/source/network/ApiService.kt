package id.arvigo.arvigobasecore.data.source.network

import id.arvigo.arvigobasecore.data.source.network.request.LoginRequest
import id.arvigo.arvigobasecore.data.source.network.response.LoginResponse
import id.arvigo.arvigobasecore.data.source.network.response.brands.BrandResponse
import id.arvigo.arvigobasecore.data.source.network.response.category.CategoryResponse
import id.arvigo.arvigobasecore.data.source.network.response.home_product.HomeProduct
import id.arvigo.arvigobasecore.data.source.network.response.personality.Personality
import id.arvigo.arvigobasecore.ui.feature.register.model.RegisterRequest
import id.arvigo.arvigobasecore.ui.feature.register.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("/v1/auth/register-user")
    fun register(
        @Body request: RegisterRequest
    ): Call<RegisterResponse>

    @POST("/v1/auth/login")
    fun login(
        @Body request: LoginRequest
    ): Call<LoginResponse>

    @GET("/v1/questionnaires")
    suspend fun getQuestionnaires(
        @Header("Authorization") token: String,
    ): Personality

    @GET("/v1/homes")
    suspend fun getHomeProduct(
        @Header("Authorization") token: String,
    ): HomeProduct

    @GET("/v1/brands")
    suspend fun getBrands(
        @Header("Authorization") token: String,
    ): BrandResponse

    @GET("/v1/categories/1/list-product")
    suspend fun getEyewearCategory(
        @Header("Authorization") token: String,
    ): CategoryResponse

    @GET("/v1/homes/search/{search}")
    suspend fun searchProduct(
        @Header("Authorization") token: String,
        @Path("search") search: String,
    ): CategoryResponse

/*    @POST("/v1/auth/login")
    suspend fun loginNew(
        @Body request: LoginRequest
    ):*/
}