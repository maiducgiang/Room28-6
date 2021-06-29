package com.example.room28_6.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "data")
data class Email(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val sender:String,
    val title: String,
    val context: String
)