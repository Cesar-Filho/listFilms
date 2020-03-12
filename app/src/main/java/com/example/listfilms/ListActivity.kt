package com.example.listfilms

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listfilms.lists.GradeListAdapter
import com.example.listfilms.model.Grade

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val list = findViewById<RecyclerView>(R.id.list_movies)
        val grades = fetchGrades().map { Grade(it.name + "a", it.imagePath) }

        list.adapter = GradeListAdapter(grades)
        list.layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
    }

    private fun fetchGrades(): List<Grade> = listOf(
        Grade(
            "1 ano",
            "https://cdn1.iconfinder.com/data/icons/ninja-things-1/1772/ninja-simple-512.png"
        ),
        Grade("2 ano", "http://i.imgur.com/DvpvklR.png"),
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