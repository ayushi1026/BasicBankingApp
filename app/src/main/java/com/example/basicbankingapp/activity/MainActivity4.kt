package com.example.basicbankingapp.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import com.example.basicbankingapp.R
import com.example.basicbankingapp.database.PersonDatabase
import com.example.basicbankingapp.database.PersonEntity
import com.example.basicbankingapp.database.TranscationDatabase
import com.example.basicbankingapp.database.TranscationEntity
import com.example.basicbankingapp.fragments.TranscationFragment
import kotlinx.android.synthetic.main.activity_main4.*

@Suppress("DEPRECATION")
class MainActivity4 : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    lateinit var etAmount: EditText
    lateinit var personEntity1: PersonEntity
    lateinit var personEntity2: PersonEntity
    lateinit var transcationEntity: TranscationEntity
    var money1: Int = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        sharedPreferences= getSharedPreferences("BankApp", Context.MODE_PRIVATE)
        val id2= intent.getIntExtra("id_2", 2)
        val id1= sharedPreferences.getInt("id_1", 1)
        etAmount = findViewById(R.id.etAmount)
        personEntity1 = DBAsyncTask3(applicationContext, id1).execute().get()
        personEntity2 = DBAsyncTask3(applicationContext, id2).execute().get()
        var balance1= personEntity1.personBalance
        var balance2=personEntity2.personBalance
        layout2.visibility= View.GONE
        var money: Int= 20000
        var count = DBAsyncTask6(applicationContext).execute().get()
        count++
        SubmitButton.setOnClickListener {
            if (etAmount.text.isBlank() || (etAmount.text.toString()).toInt() == 0){
                etAmount.error= "Please Enter Amount"
            }
            else if((etAmount.text.toString()).toInt() > balance1){
                ImageTranscation.setImageResource(R.drawable.errorimage)
                TransText.text= "Transcation Failed"
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                TransName1.text="Sender: ${personEntity1.personName}"
                TransName2.text="Receiver: ${personEntity2.personName}"
                TransAmount.text="Amount: Failed"
                layout2.visibility = View.VISIBLE
                transcationEntity = TranscationEntity(count, personEntity1.personName , personEntity2.personName, false, "Failed")
                DBAsyncTask5(applicationContext, transcationEntity).execute()
                doneButton.setOnClickListener{
                    onBackPressed()
                }
            }
            else{
                money = (etAmount.text.toString()).toInt()
                balance1=balance1-money
                balance2=balance2+money
                val async1 = DBAsyncTask4(applicationContext, balance1, id1).execute().get()
                val async2 = DBAsyncTask4(applicationContext, balance2, id2).execute().get()
                TransName1.text="Sender: ${personEntity1.personName}"
                TransName2.text="Receiver: ${personEntity2.personName}"
                TransAmount.text="Amount: Rs $money"
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                layout2.visibility = View.VISIBLE
                transcationEntity = TranscationEntity(count, personEntity1.personName , personEntity2.personName, true, money.toString())
                DBAsyncTask5(applicationContext, transcationEntity).execute()
                doneButton.setOnClickListener{
                    onBackPressed()
                }
            }
        }
    }

    @Suppress("DEPRECATION")
    class DBAsyncTask3(val context: Context, val id: Int) : AsyncTask<Void, Void, PersonEntity>() {
        val db = Room.databaseBuilder(context, PersonDatabase::class.java, "person-db").build()
        override fun doInBackground(vararg params: Void?): PersonEntity {
            return db.personDao().getsingleData(id)
        }
    }

    class DBAsyncTask4(val context: Context, val value: Int, val person_id: Int) : AsyncTask<Void, Void, Boolean>() {
        val db = Room.databaseBuilder(context, PersonDatabase::class.java, "person-db").build()
        override fun doInBackground(vararg params: Void?): Boolean {
            db.personDao().updateData(person_id, value)
            return true
        }
    }

    class DBAsyncTask5(val context: Context, val transcationEntity: TranscationEntity) : AsyncTask<Void, Void, Void>(){
        val db = Room.databaseBuilder(context, TranscationDatabase::class.java, "transcation-db").build()
        override fun doInBackground(vararg params: Void?): Void? {
            db.transcationDao().insertData(transcationEntity)
            return null
        }

    }

    class DBAsyncTask6(val context: Context) : AsyncTask<Void, Void, Int>() {
        val db = Room.databaseBuilder(context, TranscationDatabase::class.java, "transcation-db").build()
        override fun doInBackground(vararg params: Void?): Int {
            return db.transcationDao().getCount()
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}