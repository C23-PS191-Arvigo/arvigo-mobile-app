package id.arvigo.arvigobasecore.data.source.network.response.personality

import com.google.gson.annotations.SerializedName

data class PersonalityResponse(

	@field:SerializedName("data")
	val data: List<PersonalityDataItem>? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class PersonalityDataItem(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("question")
	val question: String? = null,
)
