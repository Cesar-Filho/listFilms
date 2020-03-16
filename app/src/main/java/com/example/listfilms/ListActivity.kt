package com.example.listfilms

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.example.listfilms.adapter.FetchList
import com.example.listfilms.api.Api
import com.example.listfilms.api.GetMoviesResponse
import com.example.listfilms.model.Movie
import com.example.listfilms.repository.MoviesRepository
import com.google.android.material.navigation.NavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ListActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    FetchList {

    private var movies: List<Movie> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        MoviesRepository.getMovies(::success, ::error)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val navigationView = findViewById<NavigationView>(R.id.navigationView)

        setSupportActionBar(toolbar)
        actionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.drawerOpen,
            R.string.drawerClose
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.favourites -> Toast.makeText(
                this@ListActivity,
                "Favourites Selected",
                Toast.LENGTH_SHORT
            ).show()
            R.id.list -> Toast.makeText(
                this@ListActivity,
                "List Selected",
                Toast.LENGTH_SHORT
            ).show()
            R.id.logout -> Toast.makeText(
                this@ListActivity,
                "Logout Selected",
                Toast.LENGTH_SHORT
            ).show()
        }
        return false
    }

    override fun fetchList(): List<Movie> {
        return this.movies
    }

    private fun success(movies: List<Movie>) {
        supportFragmentManager.beginTransaction().add(R.id.main, ListFragment()).commit()
        this.movies = movies
    }

    private fun error() {
        Toast.makeText(this, "Invalid Parameters", Toast.LENGTH_LONG)
            .show()
    }
}
