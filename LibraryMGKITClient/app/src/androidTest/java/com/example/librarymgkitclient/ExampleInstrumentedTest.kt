package com.example.librarymgkitclient

import android.app.Activity
import android.content.Context
import android.view.WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
import android.view.WindowManager.LayoutParams.TYPE_TOAST
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Root
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.librarymgkitclient.Models.AppDatabase
import com.example.librarymgkitclient.Models.BookModel
import com.example.librarymgkitclient.Models.BookModelDao
import com.example.librarymgkitclient.ToastMatcher.Companion.onToast
import org.hamcrest.CoreMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun goToTheMenu() {
        // Context of the app under test.
        Espresso.onView(ViewMatchers.withId(R.id.etEmail)).perform(ViewActions.typeText("test"))
        Espresso.onView(ViewMatchers.withId(R.id.etPass)).perform(ViewActions.typeText("12345"))
        Espresso.onView(ViewMatchers.withId(R.id.LogIn)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.activity_menu))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)
    @Test
    fun goToTheRegister() {
        // Context of the app under test.
        Espresso.onView(ViewMatchers.withId(R.id.bReg)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.activity_register))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    /*@Test
    fun UncorrectLogin() {
        Espresso.onView(ViewMatchers.withId(R.id.LogIn)).perform(ViewActions.click())
        /*onToast(R.string.authError)
            .inRoot(ToastMatcher.isToast())
            .check(matches(isDisplayed()))*/
       // Espresso.onView(ViewMatchers.withId(R.id.activity_menu))
        //    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            var activity: Activity?=null
        activityScenarioRule.scenario.onActivity { activity=it }
        Espresso.onView(withText(R.string.authError)).inRoot(
            RootMatchers.withDecorView(
                CoreMatchers.not(
                    CoreMatchers.`is`(
                        activity!!.window.decorView
                    )
                )
            )
        ).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
    }*/

}
class ExampleUnitTest {
    private lateinit var bookModelDao: BookModelDao
    private lateinit var db: AppDatabase

    @Before
    fun createDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
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
class ToastMatcher(private val maxFailures: Int = DEFAULT_MAX_FAILURES) : TypeSafeMatcher<Root>() {

    /** Restrict number of false results from matchesSafely to avoid endless loop */
    private var failures = 0

    override fun describeTo(description: Description) {
        description.appendText("is toast")
    }

    public override fun matchesSafely(root: Root): Boolean {
        val type = root.windowLayoutParams.get().type
        @Suppress("DEPRECATION") // TYPE_TOAST is deprecated in favor of TYPE_APPLICATION_OVERLAY
        if (type == TYPE_TOAST || type == TYPE_APPLICATION_OVERLAY) {
            val windowToken = root.decorView.windowToken
            val appToken = root.decorView.applicationWindowToken
            if (windowToken === appToken) {
                // windowToken == appToken means this window isn't contained by any other windows.
                // if it was a window for an activity, it would have TYPE_BASE_APPLICATION.
                return true
            }
        }
        // Method is called again if false is returned which is useful because a toast may take some time to pop up. But for
        // obvious reasons an infinite wait isn't of help. So false is only returned as often as maxFailures specifies.
        return (++failures >= maxFailures)
    }

    companion object {

        /** Default for maximum number of retries to wait for the toast to pop up */
        private const val DEFAULT_MAX_FAILURES = 5

        fun onToast(text: String, maxRetries: Int = DEFAULT_MAX_FAILURES) = onView(withText(text)).inRoot(isToast(maxRetries))!!

        fun onToast(textId: Int, maxRetries: Int = DEFAULT_MAX_FAILURES) = onView(withText(textId)).inRoot(isToast(maxRetries))!!

        fun isToast(maxRetries: Int = DEFAULT_MAX_FAILURES): Matcher<Root> {
            return ToastMatcher(maxRetries)
        }
    }

}