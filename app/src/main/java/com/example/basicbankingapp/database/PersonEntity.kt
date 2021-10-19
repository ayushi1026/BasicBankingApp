package com.example.basicbankingapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class PersonEntity(
        @PrimaryKey val id: Int,
        @ColumnInfo(name = "person_name")  val personName: String,
        @ColumnInfo(name = "person_email")  val personEmail: String,
        @ColumnInfo(name = "person_phone")  val personNumber: String,
        @ColumnInfo(name = "person_image")  val personImage: Int,
        @ColumnInfo(name = "person_gender")  val personGender: String,
        @ColumnInfo(name = "person_balance")  val personBalance: Int
)