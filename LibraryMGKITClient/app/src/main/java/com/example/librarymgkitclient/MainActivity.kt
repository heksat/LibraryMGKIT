package com.example.librarymgkitclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

public interface APILibraryMGKIT{
    @GET("/api/Authors/authors")
    fun logIn(): Call<MutableList<Author>>

}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // var gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.).create()

        var retrofit = Retrofit.Builder().baseUrl("http://192.168.0.202:57702/").addConverterFactory(
            GsonConverterFactory.create()).build()
        var publictest = retrofit.create(APILibraryMGKIT::class.java)
        var but = findViewById<Button>(R.id.LogIn)
        var list = mutableListOf<Author>()
        but.setOnClickListener(){
            publictest.logIn().enqueue(object :Callback<MutableList<Author>>{
                override fun onResponse(
                    call: Call<MutableList<Author>>,
                    response: Response<MutableList<Author>>
                ) {
                    var result = response.body()
                    if(result!=null) {
                        list = result
                    }
                }

                override fun onFailure(call: Call<MutableList<Author>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Ошибка",Toast.LENGTH_SHORT)
                }
            })
        }
    }
}
class Author{
    var ID:UUID? = null
    var LName:String? = null
    var FName:String? = null
    var SName:String? = null
    var BirthDay:String? = null
    var Gender:Char? = null

}