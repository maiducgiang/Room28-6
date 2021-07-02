package com.example.room28_6.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.room28_6.entity.Email

@Dao
interface EmailDao {
    @Insert
    fun insertData(data: List<Email>)

    @Query("select * from data")
    fun getData() : LiveData<List<Email>>
}