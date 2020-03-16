package com.example.listfilms.adapter

import com.example.listfilms.model.Movie

interface FetchList {
    fun fetchList() : List<Movie>
}