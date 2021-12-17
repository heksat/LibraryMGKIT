package com.example.librarymgkitclient.Models

import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.*
import java.util.*
import java.util.stream.Collectors

@Entity
class BookModel {
    @ColumnInfo(name = "ID")
    @PrimaryKey(autoGenerate = false)
    @TypeConverters(BookConverter::class)
    var ID: UUID = UUID.randomUUID()
    @ColumnInfo(name = "Author")
    var Author: String? = null
    @ColumnInfo(name = "Name")
    var Name: String? = null
    @ColumnInfo(name = "Count")
    var Count: Int? = null
    @ColumnInfo(name = "YearEdition")
    var YearEdition: Int? = null
    @ColumnInfo(name = "Image")
    var Image:String? = null
}
public class BookConverter {
    @TypeConverter
    public fun fromID(item:UUID?):String{
        return item.toString()
    }
    @TypeConverter
    public fun ToID(data:String):UUID?{
        return UUID.fromString(data)
    }

}

@Dao
interface BookModelDao {
    @androidx.room.Query("SELECT * FROM BookModel")
    fun getAll():MutableList<BookModel>
    @Insert
    fun insertAll(vararg marics: BookModel)
    @androidx.room.Query("DELETE FROM BookModel")
    fun dropAll()
    @Delete
    fun delete(item:BookModel)
}
@Database(entities = [BookModel::class],version = 1)
abstract class AppDatabase:RoomDatabase(){
    abstract fun BookModelDao(): BookModelDao
}