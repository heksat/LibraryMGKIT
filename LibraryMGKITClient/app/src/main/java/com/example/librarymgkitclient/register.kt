package com.example.librarymgkitclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.RelativeSizeSpan
import android.widget.*
import androidx.core.view.get
import com.example.librarymgkitclient.Models.LoginModel
import com.example.librarymgkitclient.Models.RegisterModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        var but = findViewById<Button>(R.id.bRegister)
        var LName = findViewById<EditText>(R.id.etRLName)
        var FName = findViewById<EditText>(R.id.etRFName)
        var SName = findViewById<EditText>(R.id.etRSName)
        var Email = findViewById<EditText>(R.id.etREmail)
        var Pass = findViewById<EditText>(R.id.etRPass)
        var calendar = findViewById<CalendarView>(R.id.calendarBirthday)
        var Gender = findViewById<Spinner>(R.id.spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.Genders,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            Gender.adapter = adapter
        }


        but.setOnClickListener(){

        if (LName.text.trim().isNullOrEmpty() || FName.text.trim().isNullOrEmpty() || Email.text.trim().isNullOrEmpty()
            || Pass.text.trim().isNullOrEmpty()){
            Toast.makeText(this,"Введите все поля, отмеченные *",Toast.LENGTH_LONG).show()
        }
        else {
            var model = RegisterModel()
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            model.BirthDay = sdf.format(Date(calendar.date))
            model.Email = Email.text.toString()
            model.LName = LName.text.toString()
            model.FName = FName.text.toString()
            model.SName = SName.text.toString()
            model.Pass = Pass.text.toString()
            model.Gender = Gender.selectedItem.toString()[0]
            RetroFit.publicapi.postregister(model).enqueue(object : Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.isSuccessful) {

                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
        }
    }
}