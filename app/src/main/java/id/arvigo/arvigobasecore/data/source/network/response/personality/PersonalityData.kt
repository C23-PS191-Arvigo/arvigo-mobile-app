package id.arvigo.arvigobasecore.data.source.network.response.personality


import com.google.gson.annotations.SerializedName

data class PersonalityData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("question")
    val question: String,
    @SerializedName("type")
    val type: String
)