package com.example.librarymgkitclient

import com.example.librarymgkitclient.Models.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import okhttp3.OkHttpClient
import java.util.*
import okhttp3.Cookie

import okhttp3.HttpUrl

import okhttp3.CookieJar




class RetroFit {
    companion object{
        var okHttpClient:OkHttpClient.Builder
        init {
            okHttpClient =  OkHttpClient().newBuilder()
            okHttpClient.cookieJar(MyCookieJar())
         //   okHttpClient.interceptors().add(ReceivedCookiesInterceptor())
        }
        var retrofit = Retrofit.Builder().baseUrl("http://192.168.0.202:57702/")
            .client(okHttpClient.build()).addConverterFactory(
            GsonConverterFactory.create()).build()
        var publicapi = retrofit.create(APILibraryMGKIT::class.java)
    }
}
//class ReceivedCookiesInterceptor: Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        var originalResponse = chain.proceed(chain.request())
 //       if (originalResponse.headers().get("Set-Cookie") == null) {
//            return originalResponse
//        }
//        return originalResponse
//    }
//
//}
class MyCookieJar : CookieJar {
    private var cookies: List<Cookie>? = null
    override fun saveFromResponse(url: HttpUrl?, cookies: List<Cookie>?) {
        this.cookies = cookies
    }

    override fun loadForRequest(url: HttpUrl?): List<Cookie> {
        return cookies ?: ArrayList()
    }
}

public interface APILibraryMGKIT{
    @POST("/api/Account/Login")
    fun logIn(@Body model:LoginModel): Call<Unit>
    @GET("/api/Authors/authors")
    fun getAuthors(): Call<MutableList<Author>>
    @GET("/api/Books")
    fun getBooks(): Call<MutableList<BookModel>>
    @POST("/api/Lendings/lending")
    fun lendingBook(@Body model:IDModel):Call<String>
    @GET("/api/Lendings")
    fun getBookLendings(): Call<MutableList<BookLendings>>
    @GET("/api/User")
    fun getUser():Call<UserModel>
    @POST("/api/User")
    fun postUser(@Body model: PostUserModel):Call<Unit>
    @POST("/api/Lendings/Back")
    fun backBook(@Body id:IDModel):Call<String?>


}