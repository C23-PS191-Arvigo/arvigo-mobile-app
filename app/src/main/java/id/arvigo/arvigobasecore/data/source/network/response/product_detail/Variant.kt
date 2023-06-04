package id.arvigo.arvigobasecore.data.source.network.response.product_detail


import com.google.gson.annotations.SerializedName

data class Variant(
    @SerializedName("is_primary_variant")
    val isPrimaryVariant: Boolean,
    @SerializedName("link_ar")
    val linkAr: String,
    @SerializedName("name")
    val name: String
)