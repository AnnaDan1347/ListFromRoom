package by.annadanilenko.listfromroom.data.api

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MyRetrofit(var baseURL: String) {

    private var restApi: INetClientAPI? = null

    private fun getHttpClientEnableSSL(): OkHttpClient {
        val versionCodeApp = "1.0"
        val interceptor = HttpLoggingInterceptor()

        val interceptorHeader = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("version", versionCodeApp)
                .build()
            chain.proceed(request)
        }


        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(interceptorHeader)
            .build()
    }

    fun createClient(): INetClientAPI? {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val client: OkHttpClient = getHttpClientEnableSSL()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        restApi = retrofit.create(INetClientAPI::class.java)
        return restApi
    }
}