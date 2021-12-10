package com.example.librarymgkitclient.Models

import com.example.librarymgkitclient.Enums.enumStatusBook
import com.example.librarymgkitclient.RetroFit
import com.example.librarymgkitclient.interfaces.Model
import com.example.librarymgkitclient.interfaces.Presenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class BookAdminLendings(var presenter: Presenter): Model {
    var ID: UUID? = null
    var Author: String? = null
    var Name: String? = null
    var YearEdition: Int? = null
    var Status: enumStatusBook? =null
    var UserLogin: String? = null
    override fun Create() {
        TODO("Not yet implemented")
    }

    override fun Edit(model:Model,onSuccess: (error:String?) -> Unit) {
        RetroFit.publicapi.returnBook(model as IDModel).enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                var result = response.body()
                if (response.isSuccessful) {
                    onSuccess(result)
                }
            }
            override fun onFailure(call: Call<String?>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun Delete() {
        TODO("Not yet implemented")
    }

    override fun Get(onSuccess: (list: MutableList<Any?>) -> Unit) {
        RetroFit.publicapi.getBookAdminLendings().enqueue(object: Callback<MutableList<BookAdminLendings>> {
            override fun onResponse(call: Call<MutableList<BookAdminLendings>>, response: Response<MutableList<BookAdminLendings>>) {
                if (response.isSuccessful){
                    var result = response.body()
                    if (result != null) {
                        onSuccess(result as MutableList<Any?>)
                    }

                }
            }
            override fun onFailure(call: Call<MutableList<BookAdminLendings>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}