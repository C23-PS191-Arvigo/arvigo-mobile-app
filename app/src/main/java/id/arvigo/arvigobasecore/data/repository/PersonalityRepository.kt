package id.arvigo.arvigobasecore.data.repository

import android.util.Log
import id.arvigo.arvigobasecore.data.source.network.ApiService
import id.arvigo.arvigobasecore.data.source.network.response.personality.Personality
import id.arvigo.arvigobasecore.data.source.network.response.personality.PersonalityDataItem
import id.arvigo.arvigobasecore.data.source.network.response.personality.PersonalityResponse
import retrofit2.HttpException
import retrofit2.Response

class PersonalityRepository(
    private val apiService: ApiService
) {

    suspend fun getPersonality(): Response<Personality> {
        val token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NSwiZnVsbF9uYW1lIjoiWXVzdWYgV2liaXNvbm8iLCJyb2xlIjoiIiwiZXhwIjoxNzE1NDM5NTE4fQ.MdIUScrs-IX8G7QVesn12irYUof5zmiaiNSWXn4ObDE"
        Log.d("Hit API", "getPersonality")
        return try {
            apiService.getQuestionnaires(
                token = token
            )
        } catch (e: HttpException) {
            Response.error(e.code(), e.response()?.errorBody()!!)
        }
    }

}