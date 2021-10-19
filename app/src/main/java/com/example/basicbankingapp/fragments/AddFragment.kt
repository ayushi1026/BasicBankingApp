package com.example.basicbankingapp.fragments

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.provider.Telephony
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import com.example.basicbankingapp.R
import com.example.basicbankingapp.database.PersonDatabase
import com.example.basicbankingapp.database.PersonEntity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class AddFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var button: Button
    lateinit var editText1: EditText
    lateinit var editText2: EditText
    lateinit var editText3: EditText
    lateinit var editText4: EditText
    lateinit var button2: Button
    lateinit var profileName: String
    lateinit var emailAddress: String
    lateinit var phoneNumber: String
    lateinit var gender: String
    lateinit var personEntity: PersonEntity
    lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        button = view.findViewById(R.id.ButtonAdd)
        editText1 = view.findViewById(R.id.etprofileName)
        editText2= view.findViewById(R.id.etEmailAddress)
        editText3= view.findViewById(R.id.etPhoneNumber)
        editText4= view.findViewById(R.id.etGender)
       //navigationView = view.findViewById(R.id.navigation_view)
        button.setOnClickListener {
            var count = DBAsyncTask(activity as Context).execute().get()
            count++
            profileName = editText1.text.toString()
            emailAddress = editText2.text.toString()
            phoneNumber= editText3.text.toString()
            gender= editText4.text.toString()
            val gender1="Male"
            val gender2="Female"
            if(gender.equals(gender1)){
                personEntity = PersonEntity(count, profileName, emailAddress, phoneNumber, R.drawable.maleuser, gender, 0)
            } else if (gender.equals(gender2)){
                personEntity = PersonEntity(count, profileName, emailAddress, phoneNumber, R.drawable.femaleuser, gender,0)
            } else{
                personEntity = PersonEntity(count, profileName, emailAddress, phoneNumber, R.drawable.maleuser, gender,0)
            }
           val async= DBAsyncTask2(activity as Context, personEntity).execute().get()
            if (async == 1){
                Toast.makeText(context, "User is created Successfully", Toast.LENGTH_SHORT).show()
               // navigationView.setCheckedItem(R.id.dashboard)
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.framelayout, DashboardFragment()).commit()
               requireActivity().navigation_view.setCheckedItem(R.id.dashboard)
            }
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    class DBAsyncTask(val context: Context) : AsyncTask<Void, Void, Int>() {
        val db = Room.databaseBuilder(context, PersonDatabase::class.java, "person-db").build()
        override fun doInBackground(vararg params: Void?): Int {
            return db.personDao().getCount()
        }
    }

    class DBAsyncTask2(val context: Context, val personEntity: PersonEntity) : AsyncTask<Void, Void, Int>() {
        val db = Room.databaseBuilder(context, PersonDatabase::class.java, "person-db").build()
        override fun doInBackground(vararg params: Void?): Int {
            db.personDao().insertSingleData(personEntity)
            return 1
        }
    }
}