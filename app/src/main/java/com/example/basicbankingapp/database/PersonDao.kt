package com.example.basicbankingapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonDao {

    @Insert
    fun insertData(personEntity: List<PersonEntity>)

    @Insert
    fun insertSingleData(personEntity: PersonEntity)

    @Query("SELECT * FROM person")
    fun getAllData(): List<PersonEntity>

    @Query("SELECT COUNT(*) FROM person")
    fun getCount(): Int

    @Query("SELECT * FROM person WHERE id = :person_id")
    fun getsingleData(person_id: Int): PersonEntity

    @Query("UPDATE person set person_balance=:value1 where id=:person_id")
    fun updateData(person_id: Int, value1: Int)

    @Query("SELECT *FROM person WHERE id != :person_id")
    fun getAllDataExceptOne(person_id: Int): List<PersonEntity>
}