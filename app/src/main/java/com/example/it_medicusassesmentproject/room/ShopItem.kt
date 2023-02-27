package com.example.it_medicusassesmentproject.room

import android.widget.ImageView
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ShopTable")
data class ShopItem(

    @ColumnInfo(name = "itemName")
    var itemName: String,

    @ColumnInfo(name = "price_text")
    var price_text: String,

    @ColumnInfo(name = "quantity_txt")
    var quantity_txt: String,

    @ColumnInfo(name = "item_img")
    var item_img: ByteArray


) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

}