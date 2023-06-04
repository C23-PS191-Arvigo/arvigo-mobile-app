package id.arvigo.arvigobasecore.ui.feature.profile

import com.google.gson.annotations.SerializedName

data class ProfileItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("avatar")
    val avatar: String
)
