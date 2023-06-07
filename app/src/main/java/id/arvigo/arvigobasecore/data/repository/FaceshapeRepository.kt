package id.arvigo.arvigobasecore.data.repository

import android.util.Log
import id.arvigo.arvigobasecore.data.source.local.AuthPreferences
import id.arvigo.arvigobasecore.data.source.network.ApiService
import id.arvigo.arvigobasecore.data.source.network.response.faceshape.FaceshapeData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class FaceshapeRepository(
    private val apiService: ApiService,
    private val authPreferences: AuthPreferences,
) {

    fun submitFaceShape(image: File) = flow {
        val token = authPreferences.getAuthToken()
        val imageBody = image.asRequestBody("image/*".toMediaTypeOrNull())
        val imgMultiPart: MultipartBody.Part = MultipartBody.Part.createFormData("image", image.name, imageBody)
        Log.d("Hit API Face Shape", "Generate FaceShape ${imageBody.toString()}")
        emit(apiService.submitFaceShape(
            token = "Bearer $token",
            image = imgMultiPart,
        ).data)
    }.flowOn(Dispatchers.IO)

}