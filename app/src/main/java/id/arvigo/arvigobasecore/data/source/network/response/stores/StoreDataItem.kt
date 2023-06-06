package id.arvigo.arvigobasecore.data.source.network.response.stores


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StoreDataItem(
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
) : Parcelable