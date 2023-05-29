package id.arvigo.arvigobasecore.data.source.network.response.personality


import com.google.gson.annotations.SerializedName

data class Personality(
    @SerializedName("data")
    val `data`: List<PersonalityData>,
    @SerializedName("message")
    val message: String
)