package com.example.basicbankingapp.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.basicbankingapp.R
import com.example.basicbankingapp.activity.MainActivity2
import com.example.basicbankingapp.database.PersonEntity

class DashboardAdapter(val context: Context, val itemList: List<PersonEntity>): RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder>() {

    class DashboardViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textview: TextView = view.findViewById(R.id.ProfileName)
        val imageView: ImageView = view.findViewById(R.id.DashboardImage)
        val EmailText: TextView = view.findViewById(R.id.EmailAddress)
        val PhoneText: TextView = view.findViewById(R.id.PhoneNumber)
        val GenderText: TextView = view.findViewById(R.id.Gender)
        val relativeLayout: RelativeLayout = view.findViewById(R.id.RelativeDashboard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dashboard_recyclerview_single, parent, false)
        return DashboardViewHolder(view)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val id = itemList[position]
        holder.textview.text = id.personName
        holder.EmailText.text=id.personEmail
        holder.PhoneText.text=id.personNumber
        holder.GenderText.setText("Gender: ${id.personGender}")
        holder.imageView.setImageResource(id.personImage)
        holder.relativeLayout.setOnClickListener {
            val intent = Intent(context, MainActivity2::class.java)
            intent.putExtra("person_id", id.id)
            context.startActivity(intent)
            //(context as Activity).finish()
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}