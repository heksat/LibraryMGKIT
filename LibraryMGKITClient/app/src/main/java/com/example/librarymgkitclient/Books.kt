package com.example.librarymgkitclient

import android.app.Instrumentation
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build.ID
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.librarymgkitclient.Models.AppDatabase
import com.example.librarymgkitclient.Models.BookModel
import com.example.librarymgkitclient.Models.BookModelDao
import com.example.librarymgkitclient.Models.IDModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class Books : AppCompatActivity() {
    var db:AppDatabase? = null
    val bookModelDao:BookModelDao? = null
    var job: Job? = null
    var cache:MutableList<BookModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)
        db = Room.databaseBuilder(this, AppDatabase::class.java,"1")
            .fallbackToDestructiveMigration().build()
        val bookModelDao = db?.BookModelDao()
        val RecyclerView = findViewById<RecyclerView>(R.id.rvBooks)
        var layout = LinearLayoutManager(this)
        RecyclerView.layoutManager = layout
       // findViewById<Button>(R.id.bLocalLoad).setOnClickListener(){
            job = GlobalScope.launch(Dispatchers.Main) {
                var job = GlobalScope.launch(Dispatchers.IO){
                    cache = bookModelDao?.getAll()
                }
                job?.join()
                RecyclerView.adapter = BooksAdapter(cache!!)
            }
        //}
     //   var intentforimage = Intent()
    //    intentforimage.action = MediaStore.ACTION_IMAGE_CAPTURE
     //   startActivityForResult(intentforimage,REQUEST_IMAGE_CAPTURE)

        RetroFit.publicapi.getBooks().enqueue(object: Callback<MutableList<BookModel>> {
            override fun onResponse(call: Call<MutableList<BookModel>>, response: Response<MutableList<BookModel>>) {
                if (response.isSuccessful){
                    var result = response.body()
                    if (result != null) {
                        GlobalScope.launch() {
                            bookModelDao!!.dropAll()
                            bookModelDao!!.insertAll(
                                *result!!.toTypedArray()
                            )
                        }
                        RecyclerView.adapter = BooksAdapter(result)
                        var bSend = findViewById<Button>(R.id.bSend)
                        bSend.setOnClickListener() {
                            var intent1 = Intent()
                            intent1.action = Intent.ACTION_SEND
                            intent1.putExtra(Intent.EXTRA_TEXT, result.map{it.Name}.joinToString(",\n"))
                            intent1.type = "text/plain"
                            var start = Intent.createChooser(intent1, null)
                            startActivity(start)
                        }
                    }

                }
            }
            override fun onFailure(call: Call<MutableList<BookModel>>, t: Throwable) {

            }

        })
    }
    companion object{
        const val REQUEST_IMAGE_CAPTURE = 1
        var img:Bitmap?=null
        val imgView:ImageView? = null
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode== REQUEST_IMAGE_CAPTURE)&&(resultCode== RESULT_OK)) {
            val bitmap = data?.extras?.get("data") as Bitmap?
            val imageView = findViewById<ImageView>(R.id.imageView)
            img = bitmap
            imageView.setImageBitmap(bitmap)
        }
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
            var img = itemView.findViewById<ImageView>(R.id.imageView)
            val rv = itemView.findViewById<RecyclerView>(R.id.rvBooks)
            var bPhoto = itemView.findViewById<Button>(R.id.bPhoto)

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
            val imageBytes = Base64.decode(books[position].Image,0)
            holder.img.setImageBitmap(BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size))
            holder.Name.text = books[position].Name
            holder.Count.text = (books[ position].Count ?: 0).toString()
            holder.Author.text = books[position].Author
            holder.YearEdition.text = (books[position].YearEdition ?: 0).toString()
            holder.bLending.setOnClickListener(){
                fill(position)
                notifyDataSetChanged()
            }
            holder.bPhoto.setOnClickListener(){
                var intentforimage = Intent()
                    intentforimage.action = MediaStore.ACTION_IMAGE_CAPTURE
                   startActivityForResult(intentforimage,REQUEST_IMAGE_CAPTURE)
            }

        }
        fun bookupdate(){
            RetroFit.publicapi.getBooks().enqueue(object: Callback<MutableList<BookModel>> {
                override fun onResponse(call: Call<MutableList<BookModel>>, response: Response<MutableList<BookModel>>) {
                    if (response.isSuccessful){
                        var result = response.body()
                        if (result != null) {
                            GlobalScope.launch() {
                                bookModelDao!!.dropAll()
                                bookModelDao!!.insertAll(
                                    *result!!.toTypedArray()
                                )
                                books.clear()
                                books.addAll(result)
                                notifyDataSetChanged()
                            }
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