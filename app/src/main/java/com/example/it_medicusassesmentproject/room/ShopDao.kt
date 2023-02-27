package com.example.it_medicusassesmentproject.room

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ShopDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg item: ShopItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllItems(items: List<ShopItem>)


    @Delete
    fun delete(item: ShopItem)


    @Update
    fun updateItem(item: ShopItem)

    @Query("UPDATE ShopTable SET price_text = :name WHERE id = :id")
    fun updateItemById(id: Int, name: String)


    @Query("SELECT * FROM ShopTable")
    fun getAllGroceryItems(): List<ShopItem>


    @Query("SELECT * FROM ShopTable WHERE itemName LIKE '%' || :search || '%'")
    fun searchItems(search: String): List<ShopItem>


//
//    @Query("SELECT * FROM ShopTable WHERE id = :id")
//    suspend fun getImageById(id: Int): ShopItem
}