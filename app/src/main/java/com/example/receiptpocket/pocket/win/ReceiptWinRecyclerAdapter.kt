package com.example.receiptpocket.pocket.win

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.receiptpocket.R
import com.example.receiptpocket.pocket.receipts.ReceiptItem

class ReceiptWinRecyclerAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<ReceiptWinRecyclerAdapter.MyViewHolder>() {

    var itemList = listOf<ReceiptWinItem>()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val dateTextView = itemView.findViewById<TextView>(R.id.date_textview)
        val storeTextView = itemView.findViewById<TextView>(R.id.store_textview)
        val codeTextView = itemView.findViewById<TextView>(R.id.code_textview)
        val priceTextView = itemView.findViewById<TextView>(R.id.price_textview)
        val prizeTextView = itemView.findViewById<TextView>(R.id.win_prize_textview)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                listener.onItemClick(itemList[position])
            }
        }

        fun bind(item: ReceiptWinItem){
            dateTextView.text = item.date
            storeTextView.text = item.store
            codeTextView.text = item.code
            priceTextView.text = item.price
            prizeTextView.text = item.prize
        }

    }

    interface OnItemClickListener {
        fun onItemClick(receiptItem: ReceiptWinItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.win_receipt_item, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


    fun updateList(list: List<ReceiptWinItem>){
        itemList = list
    }
}

