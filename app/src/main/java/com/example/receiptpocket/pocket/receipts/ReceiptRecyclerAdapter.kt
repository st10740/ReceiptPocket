package com.example.receiptpocket.pocket.receipts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.receiptpocket.R

class ReceiptRecyclerAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<ReceiptRecyclerAdapter.MyViewHolder>() {

    var itemList = listOf<ReceiptItem>()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val dateTextView = itemView.findViewById<TextView>(R.id.date_textview)
        val storeTextView = itemView.findViewById<TextView>(R.id.store_textview)
        val codeTextView = itemView.findViewById<TextView>(R.id.code_textview)
        val priceTextView = itemView.findViewById<TextView>(R.id.price_textview)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                listener.onItemClick(itemList[position])
            }
        }

        fun bind(item: ReceiptItem){
            dateTextView.setText(item.date)
            storeTextView.setText(item.store)
            codeTextView.setText(item.code)
            priceTextView.setText(item.price)
        }

    }

    interface OnItemClickListener {
        fun onItemClick(receiptItem: ReceiptItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.receipt_item, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun updateList(list: List<ReceiptItem>){
        itemList = list
    }
}

