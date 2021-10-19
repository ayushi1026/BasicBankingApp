package com.example.basicbankingapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TranscationDao {

    @Insert
    fun insertData(transcationEntity: TranscationEntity)

    @Query("SELECT * FROM transcation")
    fun getAllData(): List<TranscationEntity>

    @Query("SELECT COUNT(*) FROM transcation")
    fun getCount(): Int

}