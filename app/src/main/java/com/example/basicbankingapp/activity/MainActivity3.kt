package com.example.basicbankingapp.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.basicbankingapp.R
import com.example.basicbankingapp.adapter.ActivityAdapter
import com.example.basicbankingapp.database.PersonDatabase
import com.example.basicbankingapp.database.PersonEntity
import kotlinx.android.synthetic.main.activity_recyclerview_single.*

@Suppress("DEPRECATION")
class MainActivity3 : AppCompatActivity() {
    lateinit var dbList: List<PersonEntity>
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ActivityAdapter
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        recyclerView= findViewById(R.id.ActivityRecyclerView)
        val person_id= intent.getIntExtra("id_1", 1)
        dbList= list(applicationContext, person_id ).execute().get()
        adapter=ActivityAdapter(this, dbList)
        layoutManager = LinearLayoutManager(this)
        recyclerView.adapter=adapter
        recyclerView.layoutManager=layoutManager
        recyclerView.addItemDecoration(
                DividerItemDecoration(recyclerView.context, (layoutManager as LinearLayoutManager).orientation)
        )
        sharedPreferences= getSharedPreferences("BankApp", Context.MODE_PRIVATE)
        sharedPreferences.edit().putInt("id_1", person_id).apply()
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    class list(val context: Context, val id: Int): AsyncTask<Void, Void, List<PersonEntity>>(), List<PersonEntity>{
        val db = Room.databaseBuilder(context, PersonDatabase::class.java, "person-db").build()
        override fun doInBackground(vararg params: Void?): List<PersonEntity>? {
           return db.personDao().getAllDataExceptOne(id)
        }

        override val size: Int
            get() = TODO("Not yet implemented")

        override fun contains(element: PersonEntity): Boolean {
            TODO("Not yet implemented")
        }

        override fun containsAll(elements: Collection<PersonEntity>): Boolean {
            TODO("Not yet implemented")
        }

        override fun get(index: Int): PersonEntity {
            TODO("Not yet implemented")
        }

        override fun indexOf(element: PersonEntity): Int {
            TODO("Not yet implemented")
        }

        override fun isEmpty(): Boolean {
            TODO("Not yet implemented")
        }

        override fun iterator(): Iterator<PersonEntity> {
            TODO("Not yet implemented")
        }

        override fun lastIndexOf(element: PersonEntity): Int {
            TODO("Not yet implemented")
        }

        override fun listIterator(): ListIterator<PersonEntity> {
            TODO("Not yet implemented")
        }

        override fun listIterator(index: Int): ListIterator<PersonEntity> {
            TODO("Not yet implemented")
        }

        override fun subList(fromIndex: Int, toIndex: Int): List<PersonEntity> {
            TODO("Not yet implemented")
        }

    }
}