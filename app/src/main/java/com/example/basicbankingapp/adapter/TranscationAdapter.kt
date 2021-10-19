package com.example.basicbankingapp.adapter

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.basicbankingapp.R
import com.example.basicbankingapp.database.TranscationEntity
import kotlinx.android.synthetic.main.transaction_recyclerview_single.view.*

class TranscationAdapter(val context: Context, val itemList: List<TranscationEntity>): RecyclerView.Adapter<TranscationAdapter.TransactionViewHolder>() {

    class TransactionViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image: ImageView = view.findViewById(R.id.TransactionImage)
        val name1: TextView = view.findViewById(R.id.SenderText)
        val name2: TextView = view.findViewById(R.id.ReceiverText)
        val amount: TextView = view.findViewById(R.id.AmountText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.transaction_recyclerview_single, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val id= itemList[position]
        holder.amount.setText("Amount: Rs ${id.amount}")
        if(id.status){
            holder.image.setImageResource(R.drawable.oksymbol)
        } else{
            holder.image.setImageResource(R.drawable.errorimage)
        }
        holder.name1.setText("Sender: ${id.name1}")
        holder.name2.setText("Receiver: ${id.name2}")
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}