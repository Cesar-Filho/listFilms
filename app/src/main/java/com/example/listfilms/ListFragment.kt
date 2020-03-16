package com.example.listfilms

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listfilms.adapter.FetchList
import com.example.listfilms.adapter.GradeListAdapter
import com.example.listfilms.model.Grade
import com.example.listfilms.model.Movie

class ListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private var listener : FetchList? = null
    private lateinit var list : List<Movie>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? FetchList
        if (listener == null) {
            throw ClassCastException("$context must implement FetchList")
        } else {
            this.list = listener?.fetchList()!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_list, container, false)
        recyclerView = v.findViewById(R.id.list_movies)
        recyclerView.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )

        val grades = fetchGrades()
        recyclerView.adapter = GradeListAdapter(grades)
        return v
    }

    private fun fetchGrades(): List<Grade> {
        return this.list.map {
            val posterPath = it.poster_path
            val imagePath = "https://image.tmdb.org/t/p/w500/$posterPath"
            Grade(it.title, imagePath)
        }
    }

}