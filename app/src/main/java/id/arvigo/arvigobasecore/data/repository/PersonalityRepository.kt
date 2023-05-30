package id.arvigo.arvigobasecore.data.repository

import android.util.Log
import id.arvigo.arvigobasecore.data.source.network.ApiService
import id.arvigo.arvigobasecore.data.source.network.response.personality.Personality
import id.arvigo.arvigobasecore.data.source.network.response.personality.PersonalityData
import id.arvigo.arvigobasecore.data.source.network.response.personality.PersonalityDataItem
import id.arvigo.arvigobasecore.data.source.network.response.personality.PersonalityResponse
import id.arvigo.arvigobasecore.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response
import java.util.concurrent.Flow

class PersonalityRepository(
    private val apiService: ApiService
) {

    fun getPersonality() = flow {
        val token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NSwiZnVsbF9uYW1lIjoiWXVzdWYgV2liaXNvbm8iLCJyb2xlIjoiIiwiZXhwIjoxNzE1NDM5NTE4fQ.MdIUScrs-IX8G7QVesn12irYUof5zmiaiNSWXn4ObDE"
        Log.d("Hit API", "getPersonality")
        emit(apiService.getQuestionnaires(
            token = token
        ).data)
    }.flowOn(Dispatchers.IO)

}