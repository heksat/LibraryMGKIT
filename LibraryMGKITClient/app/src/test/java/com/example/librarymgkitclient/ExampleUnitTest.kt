package com.example.librarymgkitclient

import android.content.Context
import androidx.room.Room
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.librarymgkitclient.Models.AppDatabase
import com.example.librarymgkitclient.Models.BookModelDao
import org.junit.After
import org.junit.Test
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.example.librarymgkitclient.Models.BookModel
import org.junit.Assert.*
import org.junit.Before
import java.io.IOException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest1 {
    private lateinit var bookModelDao: BookModelDao
    private lateinit var db: AppDatabase

    @Before
    fun createDB() {
        val context = getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context.applicationContext, AppDatabase::class.java).build()
        bookModelDao = db.BookModelDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }


    @Test
    @Throws(Exception::class)
    fun testInsert() {
        val currentDB = db.BookModelDao()
        val result = currentDB.getAll()
        val sizeBefore = result.size

        bookModelDao.insertAll(
            BookModel(),
            BookModel(),
            BookModel(),
            BookModel()
        )

        assert(currentDB.getAll().size > sizeBefore)
    }

    @Test
    @Throws(Exception::class)
    fun testDelete() {
        val currentDB = db.BookModelDao()
        bookModelDao.insertAll(
            BookModel(),
            BookModel(),
            BookModel(),
            BookModel()
        )
        val result = currentDB.getAll()
        val sizeBefore = result.size
        bookModelDao.delete(result[0])

        assert(sizeBefore > currentDB.getAll().size)
    }

    @Test
    @Throws(Exception::class)
    fun testDropAll() {
        val currentDB = db.BookModelDao()
        bookModelDao.insertAll(
            BookModel(),
            BookModel(),
            BookModel(),
            BookModel()
        )
        val result = currentDB.getAll()
        val sizeBefore = result.size
        bookModelDao.dropAll()

        assert(sizeBefore > currentDB.getAll().size)
    }
}