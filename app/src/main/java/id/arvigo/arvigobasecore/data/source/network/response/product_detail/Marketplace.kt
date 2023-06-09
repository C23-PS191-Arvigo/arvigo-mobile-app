package id.arvigo.arvigobasecore.data.source.network.response.product_detail


import com.google.gson.annotations.SerializedName

data class Marketplace(
    @SerializedName("address")
    val address: String,
    @SerializedName("brand")
    val brand: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("marketplace_link")
    val marketplaceLink: String,
    @SerializedName("marketplace_name")
    val marketplaceName: String,
    @SerializedName("merchant")
    val merchant: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("store_type")
    val storeType: String
)