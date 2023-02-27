package com.example.it_medicusassesmentproject.activities

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide.init
import com.example.it_medicusassesmentproject.R
import com.example.it_medicusassesmentproject.adapters.ItemAdapter
import com.example.it_medicusassesmentproject.databinding.ActivityBagBinding
import com.example.it_medicusassesmentproject.room.ShopDatabase
import com.example.it_medicusassesmentproject.room.ShopItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class BagActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBagBinding
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var list: ArrayList<ShopItem>
    private lateinit var shopItem: ShopItem
    private lateinit var bagRecyclerView: RecyclerView
    private lateinit var shopDatabase: ShopDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBagBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        inits()
    }

    private fun inits() {
        bagRecyclerView = findViewById(R.id.bagRecyclerView)
        bagRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        bagRecyclerView.setHasFixedSize(true)

        list = ArrayList<ShopItem>()
        shopDatabase = ShopDatabase.invoke(applicationContext)
        CoroutineScope(Dispatchers.IO).launch { list.addAll(shopDatabase.getItemDao().getAllGroceryItems()) }
        Log.d("KDSFDSFC", "Size In Get All Time  "+list.size)


        bagRecyclerView.setHasFixedSize(true)
        itemAdapter = ItemAdapter(list)
        bagRecyclerView.adapter = itemAdapter

//        getAllItems()
//
//        Toast.makeText(applicationContext, " List Size ${list.size}", Toast.LENGTH_SHORT).show()
//
//        Log.d("KDSFDSFC", "Size In Init  "+list.size)
    }

    private fun getAllItems(): ArrayList<ShopItem> {
        list = ArrayList<ShopItem>()
        shopDatabase = ShopDatabase.invoke(applicationContext)
        CoroutineScope(Dispatchers.IO).launch {
            list.addAll(shopDatabase.getItemDao().getAllGroceryItems())
        }

        Log.d("KDSFDSFC", "Size In Get All Time  "+list.size)

        return list
    }

    fun drawableToByte(drawable: Int): ByteArray {

        val bitmap = BitmapFactory.decodeResource(resources, drawable)
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val byteArray = stream.toByteArray()


        return byteArray
    }

}