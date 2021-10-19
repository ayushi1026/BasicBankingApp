package com.example.basicbankingapp.fragments

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.basicbankingapp.R
import com.example.basicbankingapp.adapter.DashboardAdapter
import com.example.basicbankingapp.database.PersonDatabase
import com.example.basicbankingapp.database.PersonEntity
import com.google.android.material.navigation.NavigationView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class DashboardFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

     var itemList = listOf<PersonEntity>()
    lateinit var  recyclerView: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var adapter: DashboardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        recyclerView = view.findViewById(R.id.DashboardRecycler)
        layoutManager = LinearLayoutManager(context)
        itemList = RetrieveFavourites(activity as Context).execute().get()
        adapter = DashboardAdapter(activity as Context, itemList)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DashboardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                DashboardFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    class RetrieveFavourites(val context: Context): AsyncTask<Void, Void, List<PersonEntity>>(), List<PersonEntity> {
        override fun doInBackground(vararg params: Void?): List<PersonEntity> {
           val db = Room.databaseBuilder(context, PersonDatabase::class.java, "person-db").build()
            return db.personDao().getAllData()
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