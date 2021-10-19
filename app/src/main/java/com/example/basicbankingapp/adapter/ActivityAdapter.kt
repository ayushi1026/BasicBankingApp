package com.example.basicbankingapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.basicbankingapp.R
import com.example.basicbankingapp.activity.MainActivity4
import com.example.basicbankingapp.database.PersonEntity

class ActivityAdapter(val context: Context, val personList: List<PersonEntity>): RecyclerView.Adapter<ActivityAdapter.ActivityAdapterViewHolder>() {

    class ActivityAdapterViewHolder(view: View): RecyclerView.ViewHolder(view){
        val name: TextView = view.findViewById(R.id.NameText)
        val email: TextView = view.findViewById(R.id.EmailText)
        val balance: TextView= view.findViewById(R.id.BalanceText)
        val relativeLayout: RelativeLayout = view.findViewById(R.id.ActivityRelativeLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityAdapterViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.activity_recyclerview_single, parent, false)
        return ActivityAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActivityAdapterViewHolder, position: Int) {
       var id= personList[position]
        holder.name.setText("Name: ${id.personName}")
        holder.balance.setText("Balance: Rs ${id.personBalance}")
        holder.email.setText("Email: ${id.personEmail}")
        holder.relativeLayout.setOnClickListener {
            val intent = Intent(context, MainActivity4::class.java)
            intent.putExtra("id_2", id.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return personList.size
    }
}