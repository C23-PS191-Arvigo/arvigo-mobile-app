package id.arvigo.arvigobasecore.data.source.network.response.category


import com.google.gson.annotations.SerializedName

data class CategoryItem(
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