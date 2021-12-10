package com.example.librarymgkitclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.librarymgkitclient.Enums.enumStatusBook
import com.example.librarymgkitclient.Models.BookLendings
import com.example.librarymgkitclient.Models.IDModel
import com.example.librarymgkitclient.Presenters.BookLendingsPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class BooksLendings : AppCompatActivity(), com.example.librarymgkitclient.interfaces.View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_lendings)
        presenter =  BookLendingsPresenter(this)
        presenter?.init()
    }
    inner class BooksLendingsAdapter(var books:MutableList<BookLendings>): RecyclerView.Adapter<BooksLendingsAdapter.BookViewHolder>(){
        //View - все элементы активити наследуются от него
        inner class BookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            //определяем свойства для onBIndViewHolder
            var Name = itemView.findViewById<TextView>(R.id.tvName)
            var Author = itemView.findViewById<TextView>(R.id.tvAuthor)
            var YearEdition = itemView.findViewById<TextView>(R.id.tvYearEdition)
            var back = itemView.findViewById<Button>(R.id.bBackLending)
        }
        //отсюда обратимся к самому адаптеру
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): BooksLendingsAdapter.BookViewHolder {
            //у каждого лайоута есть описание, и у них есть некая иерархия элементов управления, и на самом деле он это делает везде скрытно, но тут нам надо сделать самостоятельно
            //мы можем получить контекст из вьюшки, а не из активити
            //inflate - создает иерархию на основе layout
            //параметры  2 - родительский элемент 3 - надо ли прикреплять
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.booklendings,parent,false)
            return  BookViewHolder(itemView)
        }
        //он вызывается тогда, когда нужно отобразить информацию о конкретном объекте в конкретном месте
        //Параметры ViewHolder  И позиция элемента
        override fun onBindViewHolder(holder: BooksLendingsAdapter.BookViewHolder, position: Int) {
            holder.Name.text = books[position].Name
            holder.Author.text = books[position].Author
            holder.YearEdition.text = (books[position].YearEdition ?: 0).toString()
            if (books[position].Status == enumStatusBook.ReturnRequest || books[position].Status == enumStatusBook.Returned){
                holder.back.setEnabled(false)
            }
            else {
                holder.back.setOnClickListener() {
                    var model = IDModel(books[position].ID)
                    //presenter?.Edit(model){if (it!=null )Toast.makeText(this@BooksLendings,it,Toast.LENGTH_LONG).show()}
                    holder.back.setEnabled(!holder.back.isEnabled)
                }
            }

        }
        override fun getItemCount(): Int = books.size
    }

    override fun onGetDataSuccess(message: String?, allCountriesData: MutableList<BookLendings>) {
        val RecyclerView = findViewById<RecyclerView>(R.id.rvBookLendings)
        val Adapter = BooksLendingsAdapter(allCountriesData)
        RecyclerView.setAdapter(Adapter)
    }
    override fun update() {
        val RecyclerView = findViewById<RecyclerView>(R.id.rvBookLendings)
        presenter?.Get{RecyclerView.adapter = BooksLendingsAdapter(it)}
        RecyclerView.adapter?.notifyDataSetChanged()
    }
    private var presenter:BookLendingsPresenter? = null
    override fun initView() {
        val RecyclerView = findViewById<RecyclerView>(R.id.rvBookLendings)
        var layout = LinearLayoutManager(this)
        RecyclerView.layoutManager = layout
        presenter?.Get{RecyclerView.adapter = BooksLendingsAdapter(it)}
    }
}