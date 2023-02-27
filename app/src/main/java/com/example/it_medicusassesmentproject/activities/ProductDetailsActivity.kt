package com.example.it_medicusassesmentproject.activities

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import com.example.it_medicusassesmentproject.R
import com.example.it_medicusassesmentproject.room.ShopDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProductDetailsActivity : AppCompatActivity() {
    lateinit var item_name: TextView
    lateinit var item_qty: TextView
    lateinit var item_price_single: TextView
    lateinit var item_price_total: TextView
    lateinit var item_image_: ImageView
    lateinit var addBtn: RelativeLayout
    lateinit var subBtn: RelativeLayout
    lateinit var add_to_bag: Button
    private lateinit var shopDatabase: ShopDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        shopDatabase = ShopDatabase.invoke(applicationContext)

        var name = intent.getStringExtra("item_name")
        val price_single = intent.getStringExtra("item_price")
        val qty = intent.getStringExtra("item_quantity")
        val image_ = intent.getByteArrayExtra("item_imgs")
        val position = intent.getIntExtra("item_position", 0)





        Log.d("LKSDFL", "onCreate: $image_")

        item_name = findViewById<TextView>(R.id.item_name_details)
        item_qty = findViewById<TextView>(R.id.item_qty_details)
        item_price_single = findViewById<TextView>(R.id.item_price_sigle_details)
        item_price_total = findViewById<TextView>(R.id.item_price_total_details)
        item_image_ = findViewById<ImageView>(R.id.item_image_details)
        addBtn = findViewById<RelativeLayout>(R.id.item_add_details)
        subBtn = findViewById<RelativeLayout>(R.id.item_subtrackt_btn_details)
        add_to_bag = findViewById<Button>(R.id.add_to_bag_details_Btn)


        item_name.text = name
        item_qty.text = qty
        item_price_single.text = price_single
        item_image_.setImageDrawable(drawableMaker(image_!!))



        addBtn.setOnClickListener {

            val textValue = item_qty.text.toString()
            val intValue = textValue.toInt()

            item_qty.text = (intValue + 1).toString()
        }

        subBtn.setOnClickListener {
            val textValue = item_qty.text.toString()
            val intValue = textValue.toInt()
            item_qty.text = (intValue - 1).toString()
        }


        add_to_bag.setOnClickListener {

            val numberStr = item_qty.text.toString()
            Toast.makeText(applicationContext, (" Submitted " + numberStr), Toast.LENGTH_SHORT).show()

            GlobalScope.launch { shopDatabase.getItemDao().updateItemById(position, numberStr) }
            startActivity(Intent(this, BagActivity::class.java))
        }


    }

    fun drawableMaker(drawable: ByteArray): Drawable {
        val byteArray = drawable
        val bitmap = byteArray?.let { BitmapFactory.decodeByteArray(byteArray, 0, it.size) }
        val drawable = BitmapDrawable(resources, bitmap)

        return drawable
    }
}