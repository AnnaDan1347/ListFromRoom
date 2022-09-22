package by.annadanilenko.listfromroom.data.model

import com.google.gson.annotations.SerializedName

data class UsersResponse(
    var serverCode: Int = 0,
    var errorText: String = "",
    @SerializedName("users")
    val users: List<User> = ArrayList<User>()
)

data class User(
    @SerializedName("login")
    val login: String = "",

    @SerializedName("avatar_url")
    val avatar_url: String = "",

    val originalUrl: String = ""
)