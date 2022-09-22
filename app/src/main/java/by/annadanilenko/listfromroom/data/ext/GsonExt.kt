package by.annadanilenko.listfromroom.data.ext

import by.annadanilenko.listfromroom.data.model.UsersResponse
import com.google.gson.Gson

fun Gson.toRemoteGetUsersInfo(body: String): UsersResponse? {
    return try {
        this.fromJson<Any>(
            body,
            UsersResponse::class.java
        ) as UsersResponse
    } catch (e: Exception) {
        null
    }
}