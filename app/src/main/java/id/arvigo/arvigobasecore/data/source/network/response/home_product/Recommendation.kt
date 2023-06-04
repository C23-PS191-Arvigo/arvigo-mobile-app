package id.arvigo.arvigobasecore.data.source.network.response.home_product


import com.google.gson.annotations.SerializedName

data class Recommendation(
    @SerializedName("brand")
    val brand: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("tags")
    val tags: List<String>
)