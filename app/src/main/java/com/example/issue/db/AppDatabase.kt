package com.example.issue.db

import android.content.Context
import androidx.room.Database
import com.example.issue.db.IssuesTable
import com.example.issue.db.CommentsTable
import androidx.room.RoomDatabase
import com.example.issue.db.IssuesDetail
import com.example.issue.db.CommentsDetail
import com.example.issue.db.AppDatabase
import androidx.room.Room

//@Database(entities = {FavTable.class}, version = 1)
@Database(entities = [IssuesTable::class, CommentsTable::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun issueDao(): IssuesDetail?
    abstract fun commentDao(): CommentsDetail?

    companion object {
        private var mInstanc: AppDatabase? = null
        @JvmStatic
        fun getInstance(con: Context): AppDatabase? {
            if (mInstanc == null) {
                mInstanc =
                    Room.databaseBuilder(con.applicationContext, AppDatabase::class.java, "MYDB")
                        .allowMainThreadQueries().build()
            }
            return mInstanc
        }
    }
}