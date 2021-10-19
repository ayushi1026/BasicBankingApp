package com.example.basicbankingapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transcation")
data class TranscationEntity(
        @PrimaryKey val id: Int,
        @ColumnInfo(name = "name1")  val name1: String,
        @ColumnInfo(name = "name2")  val name2: String,
        @ColumnInfo(name = "status")  val status: Boolean,
        @ColumnInfo(name = "amount")  val amount: String,
)
