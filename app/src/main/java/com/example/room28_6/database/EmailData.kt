package com.example.room28_6.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.room28_6.dao.EmailDao
import com.example.room28_6.entity.Email
import com.example.room28_6.ioThread
import kotlinx.coroutines.InternalCoroutinesApi

@Database(
    entities = [Email :: class],
    version = 1,
    exportSchema = false
)

abstract class EmailDataBase : RoomDatabase() {

    abstract fun emailDao() : EmailDao

    @InternalCoroutinesApi
    companion object{
        @Volatile
        private var INSTANCE: EmailDataBase? = null

        fun getInstance(context: Context): EmailDataBase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            databaseBuilder(context.applicationContext,
                EmailDataBase::class.java, "email_database")
                // prepopulate the database after onCreate was called
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // insert the data on the IO Thread
//                        CoroutineScope(Dispatchers.IO).launch {
//                            getInstance(context).emailDao().addEmail(PREPOPULATE_DATA)
//                        }
                        ioThread {
                            getInstance(context).emailDao().insertData(PREPOPULATE_DATA)
                        }
                    }
                })
                .fallbackToDestructiveMigration()
                .build()

        val PREPOPULATE_DATA = listOf(Email( "val", "demo", "demo"),
            Email( "val 2", "demo", "demo"),
            Email( "val 2", "demo", "demo") )
    }
}