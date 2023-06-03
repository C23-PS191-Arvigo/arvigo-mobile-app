package id.arvigo.arvigobasecore.data.source.network.response.stores


import com.google.gson.annotations.SerializedName

data class StoreData(
    @SerializedName("location")
    val location: String,
    @SerializedName("merchant_name")
    val merchantName: String,
    @SerializedName("products")
    val storeDataItems: List<StoreDataItem>
)