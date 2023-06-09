package id.arvigo.arvigobasecore.data.source.network.response.faceshape


import com.google.gson.annotations.SerializedName

data class FaceshapeData(
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("result")
    val result: String
)