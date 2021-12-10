package com.example.librarymgkitclient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AdminMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_menu)
        var bAdmPut = findViewById<Button>(R.id.bAdmLendings)
        bAdmPut.setOnClickListener(){
            val intent = Intent(this, AdminLendings::class.java)
            startActivity(intent)
        }
    }

}