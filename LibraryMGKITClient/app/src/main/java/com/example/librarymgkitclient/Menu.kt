package com.example.librarymgkitclient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

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
    }

}