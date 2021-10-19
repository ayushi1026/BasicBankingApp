package com.example.basicbankingapp.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.room.Room
import com.example.basicbankingapp.R
import com.example.basicbankingapp.database.PersonDatabase
import com.example.basicbankingapp.database.PersonEntity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_main2.*

@Suppress("DEPRECATION")
class MainActivity2 : AppCompatActivity() {
    lateinit var  personEntity: PersonEntity
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setuptoolbar()
        var person_id: Int? = intent.getIntExtra("person_id", 1)
        personEntity = DBAsyncTask3(applicationContext, person_id as Int).execute().get()
        NameActivity.setText("Name: ${personEntity.personName}")
        PhoneActivity.setText("Mobile: ${personEntity.personNumber}")
        EmailActivity.setText("Email: ${personEntity.personEmail}")
        CurrentBalance.setText("Current Balance: Rs ${personEntity.personBalance}")
        ImageActivity.setImageResource(personEntity.personImage)
        NextButton.setOnClickListener {
            val intent= Intent(this, MainActivity3::class.java)
            intent.putExtra("id_1", person_id)
            startActivity(intent)
        }
    }

    fun setuptoolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title= "Basic Banking App"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id ==  android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }


    @Suppress("DEPRECATION")
    class DBAsyncTask3(val context: Context, val id: Int) : AsyncTask<Void, Void, PersonEntity>() {
        val db = Room.databaseBuilder(context, PersonDatabase::class.java, "person-db").build()
        override fun doInBackground(vararg params: Void?): PersonEntity {
            return db.personDao().getsingleData(id)
        }
    }

}