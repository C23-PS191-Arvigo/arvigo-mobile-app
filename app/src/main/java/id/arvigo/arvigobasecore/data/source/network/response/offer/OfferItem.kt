package id.arvigo.arvigobasecore.data.source.network.response.offer


import com.google.gson.annotations.SerializedName

data class OfferItem(
    @SerializedName("address")
    val address: String,
    @SerializedName("brand")
    val brand: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("marketplace_link")
    val marketplaceLink: String,
    @SerializedName("marketplace_name")
    val marketplaceName: Any,
    @SerializedName("merchant")
    val merchant: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("store_type")
    val storeType: String
)