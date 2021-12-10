package com.example.librarymgkitclient.Models

import com.example.librarymgkitclient.interfaces.Model
import java.util.*

class IDModel(newid:UUID?):Model {
    var ID: UUID? = null
    init{
        ID = newid
    }

    override fun Create() {
        TODO("Not yet implemented")
    }

    override fun Edit(model: Model, onSuccess: (error: String?) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun Delete() {
        TODO("Not yet implemented")
    }

    override fun Get(onSuccess: (list: MutableList<Any?>) -> Unit) {
        TODO("Not yet implemented")
    }
}