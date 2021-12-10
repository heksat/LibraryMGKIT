package com.example.librarymgkitclient.interfaces

import com.example.librarymgkitclient.Models.BookLendings
import com.example.librarymgkitclient.Models.IDModel

interface Presenter {
    fun Edit(model:Model,onSuccess: (error:String?) -> Unit)
    fun Get(onSuccess: (list: MutableList<BookLendings>) -> Unit)
    fun init()
    fun onSuccess(message: String?, allCountriesData: MutableList<BookLendings>)
    fun onFailure(message: String?)
}