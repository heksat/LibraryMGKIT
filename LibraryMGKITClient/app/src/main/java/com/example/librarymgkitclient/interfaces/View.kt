package com.example.librarymgkitclient.interfaces

import com.example.librarymgkitclient.Models.BookLendings

interface View {
    fun update()
    fun initView()
    fun onGetDataSuccess(message: String?, allCountriesData: MutableList<Any?>)
}