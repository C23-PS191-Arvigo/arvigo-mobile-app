package id.arvigo.arvigobasecore.ui.feature.subscription.model

import com.google.gson.annotations.SerializedName

data class SubscriptionRequest(
    @SerializedName("price")
    val price: Int,
    @SerializedName("unique_code")
    val uniqueCode: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("bank")
    val bank: String
)