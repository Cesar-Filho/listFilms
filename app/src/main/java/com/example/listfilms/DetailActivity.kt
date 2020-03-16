package com.example.listfilms

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.listfilms.model.Favourite
import com.example.listfilms.model.Movie
import com.example.listfilms.persistence.MainDataBase.getInstance
import com.example.listfilms.repository.MoviesRepository
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var movie: Movie
    private var favourite: Favourite? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        byId(intent.getStringExtra("ID").toInt())
        val buttonDetail = findViewById<Button>(R.id.buttonDetail)

        val get = Runnable {
            val appDatabase =
                getInstance(applicationContext)
            Log.i("EMAIL", UserSingleton.getUser())
            favourite = appDatabase?.movieFavouriteDao()?.byEmail(UserSingleton.getUser())

            buttonDetail.text = "Remover de Favoritos"
            if (favourite === null) {
                buttonDetail.text = "Adicionar aos favoritos"
            }
            Log.i("TASK", favourite?.email.toString())
        }
        AsyncTask.execute(get);
        buttonDetail.setOnClickListener(this)
    }

    private fun byId(id: Int) {
        MoviesRepository.getMovie(id, ::success, ::error)
    }

    private fun success(movie: Movie) {
        this.movie = movie
        val overView = findViewById<TextView>(R.id.overView)
        val image = findViewById<ImageView>(R.id.detail_image)
        overView.text = movie.overview
        val posterPath = movie.poster_path
        Picasso.get()
            .load("https://image.tmdb.org/t/p/w500/$posterPath")
            .resize(150, 150)
            .centerInside()
            .into(image)
    }

    private fun error() {
        Toast.makeText(this, "Invalid Parameters", Toast.LENGTH_LONG)
            .show()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.buttonDetail -> toggleFavourite()
        }
    }

    private fun toggleFavourite() {
        Log.i("ToGGLE", favourite?.email.toString())
        if (favourite !== null) {
            val delete = Runnable {
                val appDatabase =
                    getInstance(applicationContext)
                val favourite =
                    Favourite(UserSingleton.getUser(), intent.getStringExtra("ID").toInt())
                appDatabase?.movieFavouriteDao()?.remove(favourite)
            }
            AsyncTask.execute(delete);
        } else {
            val insert = Runnable {
                val appDatabase =
                    getInstance(applicationContext)
                val favourite =
                    Favourite(UserSingleton.getUser(), intent.getStringExtra("ID").toInt())
                appDatabase?.movieFavouriteDao()?.save(favourite)
                Log.i("FAVOURITE", favourite.movieId.toString())
            }
            AsyncTask.execute(insert);
        }

    }

}
