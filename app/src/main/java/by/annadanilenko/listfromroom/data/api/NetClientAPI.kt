package by.annadanilenko.listfromroom.data.api

import com.google.gson.JsonElement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import okhttp3.Request
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class NetClientAPI @Inject constructor(
    private val clientHTTP: INetClientAPI?
) {

    data class MyResponse(
        var code: Int = 0,
        var error: Boolean = false,
        var e: Exception? = null,
        var message: String? = "Timeout",
        var body: Any? = null,
        var headers: Any? = null,
        var errorBody: Any? = null,
        var errorMessage: String? = null,
        var url: String = ""
    )

    private suspend fun getMyResponse(
        deferredResponse: Deferred<Response<JsonElement>>,
        request: Request?,
    ): MyResponse {
        val myResponse = MyResponse()
        var response: Response<JsonElement>? = null
        try {
            response = deferredResponse.await()

            myResponse.error = response.code() != 200
            myResponse.code = response.code()
            myResponse.body = response.body()

            myResponse.headers = response.headers()
            myResponse.errorBody = response.errorBody()
            myResponse.url = response.raw().request.url.toString()
        } catch (e: Exception) {
            myResponse.error = true
            myResponse.e = e
            myResponse.message = e.message ?: "Timeout"
            myResponse.url = request?.url.toString()
            myResponse.code = response?.code() ?: 0
        }
        return myResponse
    }

    private suspend fun runRequest(param: Call<JsonElement>?): MyResponse {
        return CoroutineScope(Dispatchers.IO).async {
            param?.execute()
        }.let {
            getMyResponse(
                deferredResponse = it as Deferred<Response<JsonElement>>,
                request = param?.request()
            )
        }
    }

    suspend fun getUsersInfo(): MyResponse {
        val dataHeader: HashMap<String, Any> = HashMap()
        dataHeader["Content-Type"] = "application/json"

        return runRequest(clientHTTP?.getUsersInfo(dataHeader))
    }
}