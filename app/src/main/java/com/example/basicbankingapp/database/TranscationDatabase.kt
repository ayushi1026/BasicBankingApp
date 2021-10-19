package com.example.basicbankingapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TranscationEntity::class], version = 1)
abstract class TranscationDatabase: RoomDatabase() {
    abstract fun transcationDao(): TranscationDao
}