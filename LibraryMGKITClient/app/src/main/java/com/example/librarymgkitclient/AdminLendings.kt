package com.example.librarymgkitclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.librarymgkitclient.Enums.enumStatusBook
import com.example.librarymgkitclient.Models.BookAdminLendings
import com.example.librarymgkitclient.Models.BookLendings
import com.example.librarymgkitclient.Models.IDModel
import com.example.librarymgkitclient.Presenters.BookAdminLendingsPresenter
import com.example.librarymgkitclient.Presenters.BookLendingsPresenter

class AdminLendings : AppCompatActivity(), com.example.librarymgkitclient.interfaces.View{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_lendings)
        presenter =  BookAdminLendingsPresenter(this)
        presenter?.init()
    }
    inner class BooksAdminLendingsAdapter(var books:MutableList<BookAdminLendings>): RecyclerView.Adapter<BooksAdminLendingsAdapter.BookViewHolder>(){
        inner class BookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            var loginUser = itemView.findViewById<TextView>(R.id.tvLoginUserAdm)
            var Name = itemView.findViewById<TextView>(R.id.tvNameAdm)
            var Author = itemView.findViewById<TextView>(R.id.tvAuthorAdm)
            var YearEdition = itemView.findViewById<TextView>(R.id.tvYearEditionAdm)
            var back = itemView.findViewById<Button>(R.id.bBackLendingAdm)
            var get = itemView.findViewById<Button>(R.id.bGetLending)

        }
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): BooksAdminLendingsAdapter.BookViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.bookadminlendings,parent,false)
            return  BookViewHolder(itemView)
        }
        override fun onBindViewHolder(holder: BooksAdminLendingsAdapter.BookViewHolder, position: Int) {
            holder.loginUser.text = books[position].UserLogin
            holder.Name.text = books[position].Name
            holder.Author.text = books[position].Author
            holder.YearEdition.text = (books[position].YearEdition ?: 0).toString()
            if (books[position].Status != enumStatusBook.ReturnRequest){
                holder.back.isEnabled = false
                holder.get.setOnClickListener() {
                    var model = IDModel(books[position].ID)
                    presenter?.Edit(model){if (it!=null ) Toast.makeText(this@AdminLendings,it,
                        Toast.LENGTH_LONG).show() else update() }
                }
            }
            else{
                holder.get.isEnabled = false
            holder.back.setOnClickListener() {
                var model = IDModel(books[position].ID)
                presenter?.Edit(model) {
                    if (it != null) Toast.makeText(
                        this@AdminLendings, it,
                        Toast.LENGTH_LONG
                    ).show() else update()
                }
            }
            }

        }
        override fun getItemCount(): Int = books.size
    }

    override fun onGetDataSuccess(message: String?, allCountriesData: MutableList<Any?>) {
        val RecyclerView = findViewById<RecyclerView>(R.id.rvBookAdminLendings)
        val Adapter = BooksAdminLendingsAdapter(allCountriesData as MutableList<BookAdminLendings>)
        RecyclerView.setAdapter(Adapter)
    }
    override fun update() {
        val RecyclerView = findViewById<RecyclerView>(R.id.rvBookAdminLendings)
        presenter?.Get{RecyclerView.adapter = BooksAdminLendingsAdapter(it as MutableList<BookAdminLendings>)}
        RecyclerView.adapter?.notifyDataSetChanged()
    }
    private var presenter: BookAdminLendingsPresenter? = null
    override fun initView() {
        val RecyclerView = findViewById<RecyclerView>(R.id.rvBookAdminLendings)
        var layout = LinearLayoutManager(this)
        RecyclerView.layoutManager = layout
        presenter?.Get{RecyclerView.adapter = BooksAdminLendingsAdapter(it as MutableList<BookAdminLendings>)}
    }

}