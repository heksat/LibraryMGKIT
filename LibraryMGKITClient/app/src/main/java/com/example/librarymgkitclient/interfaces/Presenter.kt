package com.example.librarymgkitclient.interfaces

import com.example.librarymgkitclient.Models.BookLendings
import com.example.librarymgkitclient.Models.IDModel

interface Presenter {
    fun Edit(model:Model,onSuccess: (error:String?) -> Unit)
    fun Get(onSuccess: (list: MutableList<Any?>) -> Unit)
    fun init()
    fun onSuccess(message: String?, allCountriesData: MutableList<Any?>)
    fun onFailure(message: String?)
}