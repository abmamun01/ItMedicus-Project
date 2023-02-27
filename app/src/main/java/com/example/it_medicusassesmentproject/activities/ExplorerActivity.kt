package com.example.it_medicusassesmentproject.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.it_medicusassesmentproject.R
import com.example.it_medicusassesmentproject.room.ShopItem
import java.util.*

class ExplorerActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explorer)


        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.shoppingHostFragment) as NavHostFragment

        val navController = navHostFragment.navController




    }



    fun searchItems(items: List<ShopItem>, searchQuery: String): List<ShopItem> {
        val filteredList = mutableListOf<ShopItem>()
        val query = searchQuery.toLowerCase(Locale.getDefault()).trim()
        for (item in items) {
            if (item.itemName.toLowerCase(Locale.getDefault()).contains(query)) {
                filteredList.add(item)
            }
        }
        return filteredList
    }

}