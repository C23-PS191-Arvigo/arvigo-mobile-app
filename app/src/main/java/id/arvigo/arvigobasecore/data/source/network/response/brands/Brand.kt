package id.arvigo.arvigobasecore.data.source.network.response.brands


import com.google.gson.annotations.SerializedName

data class Brand(
    @SerializedName("id")
    val id: Int,
    @SerializedName("category_id")
    val category_id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String
)