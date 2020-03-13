package com.example.listfilms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listfilms.lists.GradeListAdapter
import com.example.listfilms.model.Grade

class ListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView

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
        val grades = fetchGrades().map { Grade(it.name, it.imagePath) }
        recyclerView.adapter = GradeListAdapter(grades)

        return v
    }

    private fun fetchGrades(): Array<Grade> = arrayOf(
        Grade(
            "1 ano",
            "https://cdn1.iconfinder.com/data/icons/ninja-things-1/1772/ninja-simple-512.png"
        ),
        Grade("2 texto", "https://cdn.pixabay.com/photo/2015/05/08/05/09/mathematics-757566_960_720.jpg"),
        Grade("3 ano", "http://i.imgur.com/DvpvklR.png"),
        Grade("4 ano", "http://i.imgur.com/DvpvklR.png"),
        Grade("5 ano", "http://i.imgur.com/DvpvklR.png"),
        Grade("6 ano", "http://i.imgur.com/DvpvklR.png"),
        Grade("1 ano", "http://i.imgur.com/DvpvklR.png"),
        Grade("1 ano", "http://i.imgur.com/DvpvklR.png"),
        Grade("1 ano", "http://i.imgur.com/DvpvklR.png"),
        Grade("1 ano", "http://i.imgur.com/DvpvklR.png"),
        Grade("1 ano", "http://i.imgur.com/DvpvklR.png"),
        Grade("1 ano", "http://i.imgur.com/DvpvklR.png"),
        Grade("1 ano", "http://i.imgur.com/DvpvklR.png"),
        Grade("1 ano", "http://i.imgur.com/DvpvklR.png"),
        Grade("1 ano", "http://i.imgur.com/DvpvklR.png"),
        Grade("1 ano", "http://i.imgur.com/DvpvklR.png"),
        Grade("1 ano", "http://i.imgur.com/DvpvklR.png"),
        Grade("1 ano", "http://i.imgur.com/DvpvklR.png")
    )
}