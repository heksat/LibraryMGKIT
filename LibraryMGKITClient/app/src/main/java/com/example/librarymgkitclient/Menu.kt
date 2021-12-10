package com.example.librarymgkitclient

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.librarymgkitclient.Models.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        var bBooks = findViewById<Button>(R.id.bBooks)
        bBooks.setOnClickListener(){
            val intent = Intent(this,Books::class.java)
            startActivity(intent)
        }
        var bLending = findViewById<Button>(R.id.bLendings)
        bLending.setOnClickListener(){
            val intent = Intent(this,BooksLendings::class.java)
            startActivity(intent)
        }
        var bAccount = findViewById<Button>(R.id.bAccount)
        bAccount.setOnClickListener(){
            val intent = Intent(this, account::class.java)
            startActivity(intent)
        }
        val intentAdmin = Intent(this,AdminMenu::class.java)
        var bAdmin = findViewById<Button>(R.id.bAdmin)
        RetroFit.publicapi.getUser().enqueue(object: Callback<UserModel> {
            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                var result = response.body()
                if (result!=null){
                    if (result.RoleCode==1){
                        bAdmin.visibility = View.VISIBLE
                        bAdmin.setOnClickListener(){
                            startActivity(intentAdmin)
                        }
                    }
                    else{
                        bAdmin.visibility = View.INVISIBLE
                    }
                }
            }

            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}