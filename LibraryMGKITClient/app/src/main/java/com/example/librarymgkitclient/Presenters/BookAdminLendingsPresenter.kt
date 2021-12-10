package com.example.librarymgkitclient.Presenters

import com.example.librarymgkitclient.Models.BookAdminLendings
import com.example.librarymgkitclient.Models.BookLendings
import com.example.librarymgkitclient.interfaces.Model
import com.example.librarymgkitclient.interfaces.Presenter
import com.example.librarymgkitclient.interfaces.View

class BookAdminLendingsPresenter(var activity: View): Presenter {
    private var model = BookAdminLendings(this)
    override fun Edit(model: Model, onSuccess: (error:String?) -> Unit) {
        this.model.Edit(model, onSuccess)
    }
    override fun Get(onSuccess: (list: MutableList<Any?>) -> Unit) {
        model.Get(onSuccess)
    }
    override fun init() {
        activity.initView()
    }
    override fun onSuccess(message: String?, allCountriesData: MutableList<Any?>) {
        activity.onGetDataSuccess(message, allCountriesData)
    }
    override  fun onFailure(message: String?) {
        //activity.onGetDataFailure(message)
    }
}