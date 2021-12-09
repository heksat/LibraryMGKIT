package com.example.librarymgkitclient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.librarymgkitclient.Models.BookModel
import com.example.librarymgkitclient.Models.PostUserModel
import com.example.librarymgkitclient.Models.UserModel
import com.example.librarymgkitclient.RetroFit.Companion.publicapi
import com.example.librarymgkitclient.RetroFit.Companion.retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class account : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        var email = findViewById<EditText>(R.id.EmailPt)
        var LName = findViewById<EditText>(R.id.LnamePT)
        var FName = findViewById<EditText>(R.id.FNamePt)
        var SName = findViewById<EditText>(R.id.SNamePt)
        var password = findViewById<EditText>(R.id.PasswordPt)
        var bBack = findViewById<Button>(R.id.bBack)
        bBack.setOnClickListener(){
            var intent = Intent(this,Menu::class.java)
            startActivity(intent)
        }

        var bChange = findViewById<Button>(R.id.bChange)
        bChange.setOnClickListener(){
            var newData:PostUserModel? = null
            if (LName.text.isNullOrEmpty() || FName.text.isNullOrEmpty() || email.text.isNullOrEmpty()){
                Toast.makeText(this,"Заполните все обязательные поля",Toast.LENGTH_LONG)
            }
            else {
                newData = PostUserModel()
                newData.Email = email.text.toString()
                newData.LName = LName.text.toString()
                newData.FName = FName.text.toString()
                newData.SName = SName.text.toString()
                if (!password.text.isNullOrEmpty()){
                    newData.Password = password.text.toString()
                }
            }
            if (newData!=null) {
                RetroFit.publicapi.postUser(newData).enqueue(object : Callback<Unit> {
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {

                    }

                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })
            }
        }
        RetroFit.publicapi.getUser().enqueue(object: Callback<UserModel> {
            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                var result = response.body()
                if (result!=null){
                    email.setText(result.Email)
                    LName.setText(result.LName)
                    FName.setText(result.FName)
                    SName.setText(result.SName)

                }
            }

            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}