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
import com.example.basicbankingapp.activity.MainActivity3
import com.example.basicbankingapp.adapter.TranscationAdapter
import com.example.basicbankingapp.database.PersonEntity
import com.example.basicbankingapp.database.TranscationDatabase
import com.example.basicbankingapp.database.TranscationEntity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TranscationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class TranscationFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var adapter: TranscationAdapter
    lateinit var itemList: List<TranscationEntity>

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
        val view = inflater.inflate(R.layout.fragment_transcation, container, false)
        itemList = Transcation(activity as Context).execute().get()
        recyclerView= view.findViewById(R.id.TranscationRecyclerView)
        adapter= TranscationAdapter(activity as Context, itemList)
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager= layoutManager
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
         * @return A new instance of fragment TranscationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TranscationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    @Suppress("DEPRECATION")
    class Transcation(val context: Context): AsyncTask<Void, Void, List<TranscationEntity>>(), List<TranscationEntity>{
        val db = Room.databaseBuilder(context, TranscationDatabase::class.java, "transcation-db").build()
        override fun doInBackground(vararg params: Void?): List<TranscationEntity> {
            return db.transcationDao().getAllData()
        }

        override val size: Int
            get() = TODO("Not yet implemented")

        override fun contains(element: TranscationEntity): Boolean {
            TODO("Not yet implemented")
        }

        override fun containsAll(elements: Collection<TranscationEntity>): Boolean {
            TODO("Not yet implemented")
        }

        override fun get(index: Int): TranscationEntity {
            TODO("Not yet implemented")
        }

        override fun indexOf(element: TranscationEntity): Int {
            TODO("Not yet implemented")
        }

        override fun isEmpty(): Boolean {
            TODO("Not yet implemented")
        }

        override fun iterator(): Iterator<TranscationEntity> {
            TODO("Not yet implemented")
        }

        override fun lastIndexOf(element: TranscationEntity): Int {
            TODO("Not yet implemented")
        }

        override fun listIterator(): ListIterator<TranscationEntity> {
            TODO("Not yet implemented")
        }

        override fun listIterator(index: Int): ListIterator<TranscationEntity> {
            TODO("Not yet implemented")
        }

        override fun subList(fromIndex: Int, toIndex: Int): List<TranscationEntity> {
            TODO("Not yet implemented")
        }

    }
}