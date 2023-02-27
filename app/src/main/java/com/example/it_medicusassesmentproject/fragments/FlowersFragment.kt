package com.example.it_medicusassesmentproject.fragments

import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.*
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.it_medicusassesmentproject.R
import com.example.it_medicusassesmentproject.activities.ExplorerActivity
import com.example.it_medicusassesmentproject.activities.ProductDetailsActivity
import com.example.it_medicusassesmentproject.adapters.ItemAdapter
import com.example.it_medicusassesmentproject.databinding.FragmentFlowersBinding
import com.example.it_medicusassesmentproject.room.ShopDatabase
import com.example.it_medicusassesmentproject.room.ShopItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream


class FlowersFragment : Fragment(), ItemAdapter.OnItemCusClickListener {

    private lateinit var binding: FragmentFlowersBinding
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var list: ArrayList<ShopItem>
    private lateinit var flowerRecyclerView: RecyclerView
    private lateinit var shopDatabase: ShopDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFlowersBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()


        list.add(ShopItem("Sun Flower", "20", "01", drawableToByte(R.drawable.img1)))
        list.add(ShopItem("Rose Flower", "35", "01", drawableToByte(R.drawable.img2)))
        list.add(ShopItem("Red Flower", "40", "01", drawableToByte(R.drawable.img3)))
        list.add(ShopItem("White Flower", "99", "01", drawableToByte(R.drawable.img4)))


        list.add(ShopItem("Sun Flower", "20", "01", drawableToByte(R.drawable.img1)))
        list.add(ShopItem("Rose Flower", "35", "01", drawableToByte(R.drawable.img2)))
        list.add(ShopItem("Red Flower", "40", "01", drawableToByte(R.drawable.img3)))
        list.add(ShopItem("White Flower", "99", "01", drawableToByte(R.drawable.img4)))


        GlobalScope.launch {
            shopDatabase.getItemDao().insertAllItems(list)
        }


    }


    private fun init() {

        shopDatabase = ShopDatabase.invoke(requireContext())

        list = ArrayList()
        flowerRecyclerView = binding.flowerItemReclerView

        flowerRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        flowerRecyclerView.setHasFixedSize(true)
        itemAdapter = ItemAdapter(list)
        flowerRecyclerView.adapter = itemAdapter

        // click listener
        itemAdapter.setClickListener(this)


    }

    fun drawableToByte(drawable: Int): ByteArray {

        val bitmap = BitmapFactory.decodeResource(resources, drawable)
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val byteArray = stream.toByteArray()


        return byteArray
    }

    override fun onItemClickk(position: Int) {
        Toast.makeText(requireContext(), "Its a toast!", Toast.LENGTH_SHORT).show()

        showCustomDialog(position)
    }

    override fun onItemViewClick(position: Int) {
        val intent = Intent(activity, ProductDetailsActivity::class.java).apply {

            putExtra("item_position", position)
            putExtra("item_name", list.get(position).itemName)
            putExtra("item_price", list.get(position).price_text)
            putExtra("item_quantity", list.get(position).quantity_txt)
            putExtra("item_imgs", list.get(position).item_img)
        }

        startActivity(intent)
    }


    private fun showCustomDialog(position: Int) {
        val dialog = Dialog(requireContext())

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
        dialog.setContentView(R.layout.dialog_box)
        dialog.setCancelable(true)

        val number = dialog.findViewById<TextView>(R.id.counterText)

        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        (dialog.findViewById<View>(R.id.btn_bag) as AppCompatButton).setOnClickListener { v ->


            val numberStr = number.text.toString()
            Toast.makeText(
                requireContext(),
                (v as AppCompatButton).text.toString() + " SUBMITTED  ${numberStr}",
                Toast.LENGTH_SHORT
            ).show()

            GlobalScope.launch { shopDatabase.getItemDao().updateItemById(position, numberStr) }


            dialog.dismiss()
        }


        // set onclick
        dialog.findViewById<RelativeLayout>(R.id.dialog_btn_add).setOnClickListener {
            val textValue = number.text.toString()
            val intValue = textValue.toInt()
            number.text = (intValue + 1).toString()

            Toast.makeText(
                requireContext(),
                " Clicked ${number.text.toString()}",
                Toast.LENGTH_SHORT
            ).show()

        }


        dialog.findViewById<RelativeLayout>(R.id.dialog_btn_subtrack).setOnClickListener {
            val textValue = number.text.toString()
            val intValue = textValue.toInt()
            number.text = (intValue - 1).toString()
        }



        dialog.show()
        dialog.window!!.attributes = lp
    }

}