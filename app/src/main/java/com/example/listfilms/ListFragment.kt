package com.example.listfilms

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listfilms.adapter.FetchList
import com.example.listfilms.adapter.GradeListAdapter
import com.example.listfilms.adapter.RecyclerItemClickListener
import com.example.listfilms.model.Grade
import com.example.listfilms.model.Movie

class ListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private var listener: FetchList? = null
    private lateinit var list: MutableList<Movie>
    private lateinit var layoutManger: LinearLayoutManager
    private lateinit var adapter: GradeListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? FetchList
        if (listener == null) {
            throw ClassCastException("$context must implement FetchList")
        } else {
            this.list = listener!!.fetchList()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_list, container, false)
        recyclerView = v.findViewById(R.id.list_movies)
        layoutManger = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
        recyclerView.layoutManager = layoutManger
        adapter = GradeListAdapter(getItems())
        recyclerView.adapter = adapter

        recyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(
                context,
                recyclerView,
                object :
                    RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        val viewId = view?.findViewById<TextView>(R.id.grade_id)
                        val id = viewId?.text
                        val intent = Intent(context, DetailActivity::class.java).apply {
                            putExtra("ID", id)
                        }
                        startActivity(intent)
                    }

                    override fun onItemLongClick(
                        view: View?,
                        position: Int
                    ) {
                        val viewId = view?.findViewById<TextView>(R.id.grade_id)
                        val id = viewId?.text
                        val intent = Intent(context, DetailActivity::class.java).apply {
                            putExtra("ID", id)
                        }
                        startActivity(intent)
                    }
                })
        )

        return v
    }

    private fun getItems(): MutableList<Grade> {
        return this.list.map {
            val posterPath = it.poster_path
            val imagePath = "https://image.tmdb.org/t/p/w500/$posterPath"
            Grade(it.title, imagePath, it.id)
        }.toMutableList()
    }

}