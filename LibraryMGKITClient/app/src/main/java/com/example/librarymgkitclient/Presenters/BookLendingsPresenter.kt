package com.example.librarymgkitclient.Presenters

import androidx.appcompat.app.AppCompatActivity
import com.example.librarymgkitclient.Models.BookLendings
import com.example.librarymgkitclient.interfaces.Model
import com.example.librarymgkitclient.interfaces.Presenter
import com.example.librarymgkitclient.interfaces.View

class BookLendingsPresenter(var activity: View):Presenter {
    private var model = BookLendings(this)
    override fun Edit(model:Model,onSuccess: (error:String?) -> Unit) {
        this.model.Edit(model, onSuccess)
    }
    override fun Get(onSuccess: (list: MutableList<BookLendings>) -> Unit) {
        model.Get(onSuccess)
    }
    override fun init() {
        activity.initView()
    }
    override fun onSuccess(message: String?, allCountriesData: MutableList<BookLendings>) {
        activity.onGetDataSuccess(message, allCountriesData)
    }
    override  fun onFailure(message: String?) {
       //activity.onGetDataFailure(message)
    }
}