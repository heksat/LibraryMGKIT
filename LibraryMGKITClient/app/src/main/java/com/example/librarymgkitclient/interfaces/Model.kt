package com.example.librarymgkitclient.interfaces

import androidx.recyclerview.widget.RecyclerView
import com.example.librarymgkitclient.Models.BookLendings

interface Model {
    fun Create()
    fun Edit(model:Model,onSuccess: (error:String?) -> Unit)
    fun Delete()
    fun Get(onSuccess: (list: MutableList<BookLendings>) -> Unit)
}