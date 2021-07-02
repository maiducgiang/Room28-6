package com.example.room28_6

import android.app.Application
import com.example.room28_6.database.EmailDataBase
import kotlinx.coroutines.InternalCoroutinesApi

class EmailResponsitory(application: Application) {
    @InternalCoroutinesApi
    val dataBase = EmailDataBase.getInstance(application)

    @InternalCoroutinesApi
    fun getAllEmail() = dataBase.emailDao().getData()
}
