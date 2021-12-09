package com.example.librarymgkitclient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.CookieManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.librarymgkitclient.Models.LoginModel
import com.example.librarymgkitclient.RetroFit.Companion.publicapi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.Calendar.getInstance


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // var gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.).create()


        var but = findViewById<Button>(R.id.LogIn)
        var list = mutableListOf<Author>()
        var email = findViewById<EditText>(R.id.etEmail)
        var pass = findViewById<EditText>(R.id.etPass)
        val intent = Intent(this,Menu::class.java)
        but.setOnClickListener(){
            var model = LoginModel()
            model.Email = email.text.toString()
            model.Password = pass.text.toString()
            publicapi.logIn(model).enqueue(object :Callback<Unit>{
                override fun onResponse(
                    call: Call<Unit>,
                    response: Response<Unit>
                ) {
                    var result = response.isSuccessful
                    if(result) {
                        CookieManager.getInstance().getCookie("http://192.168.0.202:57702/")
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this@MainActivity, "Ошибка",Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Ошибка",Toast.LENGTH_SHORT).show()
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