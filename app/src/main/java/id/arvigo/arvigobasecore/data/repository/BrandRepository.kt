package id.arvigo.arvigobasecore.data.repository

import android.util.Log
import id.arvigo.arvigobasecore.data.source.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class BrandRepository(private val apiService: ApiService) {

    fun getBrand() = flow {
        val token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NSwiZnVsbF9uYW1lIjoiWXVzdWYgV2liaXNvbm8iLCJyb2xlIjoiIiwiZXhwIjoxNzE1NDM5NTE4fQ.MdIUScrs-IX8G7QVesn12irYUof5zmiaiNSWXn4ObDE"
        Log.d("Hit API", "get Home Product")
        emit(apiService.getBrands(
            token = token
        ).data)
    }.flowOn(Dispatchers.IO)

}