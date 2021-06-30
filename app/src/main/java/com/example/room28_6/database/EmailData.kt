package com.example.room28_6.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.room28_6.dao.EmailDao
import com.example.room28_6.entity.Email
import com.example.room28_6.ioThread

@Database(entities = arrayOf(Email::class), version = 1)
abstract class EmailData : RoomDatabase() {

    abstract fun dataDao(): EmailDao

    companion object {

        @Volatile private var INSTANCE: EmailData? = null

        fun getInstance(context: Context): EmailData =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                EmailData::class.java, "Sample.db")
                // prepopulate the database after onCreate was called
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // insert the data on the IO Thread
                        ioThread {
                            getInstance(context).dataDao().insertData(PREPOPULATE_DATA)
                        }
                    }
                })
                .build()

        val PREPOPULATE_DATA = listOf(Email(1, "val", "demo", "demo"),
            Email(2, "val 2", "demo", "demo"))
    }
}