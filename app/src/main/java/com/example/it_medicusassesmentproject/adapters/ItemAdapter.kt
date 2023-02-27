package com.example.it_medicusassesmentproject.adapters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.it_medicusassesmentproject.R
import com.example.it_medicusassesmentproject.room.ShopItem
import kotlinx.coroutines.withContext

class ItemAdapter(val list: ArrayList<ShopItem>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private var clickListener: OnItemCusClickListener? = null
    fun setClickListener(listener: OnItemCusClickListener) {
        clickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.addBtn.setOnClickListener {
            clickListener?.onItemClickk(position)
        }

        holder.itemView.setOnClickListener {
            clickListener?.onItemViewClick(position)
        }

        holder.itemText.text = list.get(position).itemName
        holder.item_price.text = "$ " + list.get(position).price_text


        val imageByteArray = list.get(position).item_img
        val bitmap = getBitmapFromByteArray(imageByteArray)
        holder.imageView.setImageBitmap(bitmap)


    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView: ImageView = itemView.findViewById(R.id.item_img)
        val itemText: TextView = itemView.findViewById(R.id.itemName)
        val item_price: TextView = itemView.findViewById(R.id.item_price)
        val addBtn: RelativeLayout = itemView.findViewById(R.id.item_add_btn)

    }

    private fun getBitmapFromByteArray(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }


    interface OnItemCusClickListener {
        fun onItemClickk(position: Int)
        fun onItemViewClick(position: Int)
    }
}