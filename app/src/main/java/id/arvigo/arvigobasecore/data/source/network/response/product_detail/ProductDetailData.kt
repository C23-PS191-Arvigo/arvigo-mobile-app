package id.arvigo.arvigobasecore.data.source.network.response.product_detail


import com.google.gson.annotations.SerializedName

data class ProductDetailData(
    @SerializedName("brand_name")
    val brandName: String,
    @SerializedName("category_name")
    val categoryName: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("link_external")
    val linkExternal: String,
    @SerializedName("marketplaces")
    val marketplaces: List<Marketplace>?,
    @SerializedName("name")
    val name: String,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("variants")
    val variants: List<Variant>,
    @SerializedName("recommendation_product")
    val recommendation: List<RecommendationItem>
)

data class RecommendationItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("brand_name")
    val brand: String
)