package id.arvigo.arvigobasecore.data.source.network.response.category


import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("data")
    val `data`: List<CategoryItem>,
    @SerializedName("message")
    val message: String
)