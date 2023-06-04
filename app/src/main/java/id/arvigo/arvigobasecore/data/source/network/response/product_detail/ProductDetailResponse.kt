package id.arvigo.arvigobasecore.data.source.network.response.product_detail


import com.google.gson.annotations.SerializedName

data class ProductDetailResponse(
    @SerializedName("data")
    val `data`: ProductDetailData,
    @SerializedName("message")
    val message: String
)