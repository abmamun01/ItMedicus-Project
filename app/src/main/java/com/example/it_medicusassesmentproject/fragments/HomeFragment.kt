package com.example.it_medicusassesmentproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.it_medicusassesmentproject.adapters.ItemAdapter
import com.example.it_medicusassesmentproject.databinding.FragmentFlowersBinding
import com.example.it_medicusassesmentproject.databinding.FragmentHomeBinding
import com.example.it_medicusassesmentproject.room.ShopDatabase
import com.example.it_medicusassesmentproject.room.ShopItem
import com.google.android.material.search.SearchBar
import com.google.android.material.tabs.TabLayoutMediator
import com.mamunsproject.ecommerce_mvvm_dg.adapters.HomeViewPagerAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var searchBar: EditText
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var list: ArrayList<ShopItem>
    private lateinit var shopDatabase: ShopDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shopDatabase = ShopDatabase.invoke(requireContext())
        list = ArrayList()




        val text = binding.searchBarEditText.text.toString()
        binding.searchBarEditText.setOnClickListener {
            val searchQuery = text.toString()

            searchItems(shopDatabase.getItemDao().getAllGroceryItems(), searchQuery)
        }

        val categoriesFragment = arrayListOf<Fragment>(
            FlowersFragment(),
            VapesFragment(),
            ExtractsFragment(),
            EdiblesFragment(),
            AccessoriesFragment()
        )


        val viewPager2Adapter =
            HomeViewPagerAdapter(categoriesFragment, childFragmentManager, lifecycle)
        binding.viewPagerHome.adapter = viewPager2Adapter
        TabLayoutMediator(binding.tabLayoutID, binding.viewPagerHome) { tab, position ->
            when (position) {
                0 -> tab.text = "Flowers"
                1 -> tab.text = "Vapes"
                2 -> tab.text = "Extracts"
                3 -> tab.text = "Edibles"
                4 -> tab.text = "Accessories"
            }
        }.attach()

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