package com.example.dagboek.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dagboek.domain.Item
import java.util.*

@Dao
interface ItemDao {

    @Query("SELECT * FROM item ORDER BY title")
    suspend fun getAll(): List<Item>

    @Query("SELECT * FROM item WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): Item?

    @Insert
    suspend fun insert(item: Item)

    @Query("SELECT * FROM item WHERE date BETWEEN :from AND :to")
    fun findItemsBetweenDates(from: Date, to: Date): List<Item>

    @Query("DELETE FROM item")
    suspend fun deleteAll()


}