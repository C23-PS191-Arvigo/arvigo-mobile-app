package id.arvigo.arvigobasecore.data.source.network.response.home_product


import com.google.gson.annotations.SerializedName

data class DataHomeProduct(
    @SerializedName("face_shapes")
    val faceShapes: Any,
    @SerializedName("personalities")
    val personalities: Any,
    @SerializedName("recommendations")
    val recommendations: List<Recommendation>
)