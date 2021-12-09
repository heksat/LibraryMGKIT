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
import com.example.librarymgkitclient.Models.BookLendings
import com.example.librarymgkitclient.Models.BookModel
import com.example.librarymgkitclient.Models.IDModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksLendings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_lendings)
        val RecyclerView = findViewById<RecyclerView>(R.id.rvBookLendings)
        var layout = LinearLayoutManager(this)
        RecyclerView.layoutManager = layout

        RetroFit.publicapi.getBookLendings().enqueue(object: Callback<MutableList<BookLendings>> {
            override fun onResponse(call: Call<MutableList<BookLendings>>, response: Response<MutableList<BookLendings>>) {
                if (response.isSuccessful){
                    var result = response.body()
                    if (result != null) {
                        RecyclerView.adapter = BooksLendingsAdapter(result)
                    }

                }
            }
            override fun onFailure(call: Call<MutableList<BookLendings>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }
    inner class BooksLendingsAdapter(var books:MutableList<BookLendings>): RecyclerView.Adapter<BooksLendingsAdapter.BookViewHolder>(){
        //View - все элементы активити наследуются от него
        inner class BookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            //определяем свойства для onBIndViewHolder
            var Name = itemView.findViewById<TextView>(R.id.tvName)
            var Author = itemView.findViewById<TextView>(R.id.tvAuthor)
            var YearEdition = itemView.findViewById<TextView>(R.id.tvYearEdition)

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

        }

        override fun getItemCount(): Int = books.size


    }
}