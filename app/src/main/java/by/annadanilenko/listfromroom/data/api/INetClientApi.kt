package by.annadanilenko.listfromroom.data.api

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Headers

interface INetClientAPI {
    @Headers(
        "Cache-Control: no-cache",
        "Content-Type: application/json"
    )

    @GET("/users")
    fun getUsersInfo(
        @HeaderMap headers: HashMap<String, Any>,
    ): Call<JsonElement>

}