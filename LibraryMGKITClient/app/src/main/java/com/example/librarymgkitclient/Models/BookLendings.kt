package com.example.librarymgkitclient.Models

import com.example.librarymgkitclient.RetroFit
import com.example.librarymgkitclient.Enums.enumStatusBook
import com.example.librarymgkitclient.Presenters.BookLendingsPresenter
import com.example.librarymgkitclient.interfaces.Model
import com.example.librarymgkitclient.interfaces.Presenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class BookLendings(var presenter: Presenter): Model {
    var ID: UUID? = null
    var Author: String? = null
    var Name: String? = null
    var YearEdition: Int? = null
    var Status: enumStatusBook? =null
    override fun Create() {
        TODO("Not yet implemented")
    }

    override fun Edit(model:Model,onSuccess: (error:String?) -> Unit) {
        RetroFit.publicapi.backBook(model as IDModel).enqueue(object :Callback<String?>{
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                var result = response.body()
                if (response.isSuccessful) {
                    onSuccess(result)
                }
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })    }

    override fun Delete() {
        TODO("Not yet implemented")
    }

    override fun Get(onSuccess: (list: MutableList<BookLendings>) -> Unit) {
        RetroFit.publicapi.getBookLendings().enqueue(object: Callback<MutableList<BookLendings>> {
            override fun onResponse(call: Call<MutableList<BookLendings>>, response: Response<MutableList<BookLendings>>) {
                if (response.isSuccessful){
                    var result = response.body()
                    if (result != null) {
                        presenter.onSuccess("",result)
                    }

                }
            }
            override fun onFailure(call: Call<MutableList<BookLendings>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}