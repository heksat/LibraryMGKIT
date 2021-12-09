package com.example.librarymgkitclient

import android.os.Build.ID
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.librarymgkitclient.Models.BookModel
import com.example.librarymgkitclient.Models.IDModel
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class Books : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)
        val RecyclerView = findViewById<RecyclerView>(R.id.rvBooks)
        var layout = LinearLayoutManager(this)
        RecyclerView.layoutManager = layout

        RetroFit.publicapi.getBooks().enqueue(object: Callback<MutableList<BookModel>> {
            override fun onResponse(call: Call<MutableList<BookModel>>, response: Response<MutableList<BookModel>>) {
                if (response.isSuccessful){
                    var result = response.body()
                    if (result != null) {
                        RecyclerView.adapter = BooksAdapter(result)
                    }

                }
            }
            override fun onFailure(call: Call<MutableList<BookModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    //Адаптер требуется для понимаиня вывода в recyclerView
    inner class BooksAdapter(public var books:MutableList<BookModel>): RecyclerView.Adapter<BooksAdapter.BookViewHolder>(){
        //View - все элементы активити наследуются от него
        inner class BookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            //определяем свойства для onBIndViewHolder
            var Name = itemView.findViewById<TextView>(R.id.tvName)
            var Count = itemView.findViewById<TextView>(R.id.tvCount)
            var Author = itemView.findViewById<TextView>(R.id.tvAuthor)
            var bLending = itemView.findViewById<Button>(R.id.bLending)
            var YearEdition = itemView.findViewById<TextView>(R.id.tvYearEdition)
            val rv = itemView.findViewById<RecyclerView>(R.id.rvBooks)

        }
        //отсюда обратимся к самому адаптеру
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): BooksAdapter.BookViewHolder {
            //у каждого лайоута есть описание, и у них есть некая иерархия элементов управления, и на самом деле он это делает везде скрытно, но тут нам надо сделать самостоятельно
            //мы можем получить контекст из вьюшки, а не из активити
            //inflate - создает иерархию на основе layout
            //параметры  2 - родительский элемент 3 - надо ли прикреплять
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.books,parent,false)
            return  BookViewHolder(itemView)
        }
        //он вызывается тогда, когда нужно отобразить информацию о конкретном объекте в конкретном месте
        //Параметры ViewHolder  И позиция элемента
        override fun onBindViewHolder(holder: BooksAdapter.BookViewHolder, position: Int) {
            holder.Name.text = books[position].Name
            holder.Count.text = (books[ position].Count ?: 0).toString()
            holder.Author.text = books[position].Author
            holder.YearEdition.text = (books[position].YearEdition ?: 0).toString()
            holder.bLending.setOnClickListener(){
                fill(position)
                notifyDataSetChanged()
            }

        }
        fun bookupdate(){
            RetroFit.publicapi.getBooks().enqueue(object: Callback<MutableList<BookModel>> {
                override fun onResponse(call: Call<MutableList<BookModel>>, response: Response<MutableList<BookModel>>) {
                    if (response.isSuccessful){
                        var result = response.body()
                        if (result != null) {
                            books.clear()
                            books.addAll(result)
                            notifyDataSetChanged()
                        }

                    }
                }
                override fun onFailure(call: Call<MutableList<BookModel>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

        }
        fun fill(position: Int){
            RetroFit.publicapi.lendingBook(IDModel(books[position].ID)).enqueue(object :Callback<String?>{
                override fun onResponse(call: Call<String?>, response: Response<String?>) {
                    if (response.isSuccessful){
                        var result = response.body()
                        if (result != null){
                            Toast.makeText(this@Books,result,Toast.LENGTH_LONG).show()
                        }
                        else{
                            bookupdate()
                        }
                    }
                    else{
                        Toast.makeText(this@Books,"Это он",Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<String?>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
        override fun getItemCount(): Int = books.size


    }
}