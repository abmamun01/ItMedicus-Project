package com.example.it_medicusassesmentproject.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ShopItem::class], version = 7)
abstract class ShopDatabase : RoomDatabase() {
    abstract fun getItemDao(): ShopDao


    companion object {

        @Volatile
        private var instanceOfShopItem: ShopDatabase? = null
        private val LOCK = Any()


        operator fun invoke(context: Context) = instanceOfShopItem ?: synchronized(LOCK) {
            instanceOfShopItem ?: createDatabase(context).also {
                instanceOfShopItem = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ShopDatabase::class.java,
                "ShopDatabase4.db"
            ).build()
    }
}