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
        getFavourite(buttonDetail)

        buttonDetail.setOnClickListener(this)
    }

    private fun byId(id: Int) {
        MoviesRepository.getMovie(id, ::success, ::error)
    }

    private fun getFavourite(buttonDetail: Button) {

        val appDatabase = getInstance(applicationContext)
        Log.i("EMAIL", UserSingleton.getUser())
        val user = appDatabase.userDao()?.byEmail(
            UserSingleton.getUser()
        )
        Log.i("TASK", user?.email.toString())

        buttonDetail.text = "Remover de Favoritos"
        if (favourite === null) {
            buttonDetail.text = "Adicionar aos favoritos"
        }

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
        getFavourite(findViewById(R.id.buttonDetail))
        Log.i("ToGGLE", favourite?.email.toString())
        if (favourite !== null) {
            val delete = Runnable {
                val appDatabase =
                    getInstance(applicationContext)
                val favourite =
                    Favourite(0, UserSingleton.getUser(), intent.getStringExtra("ID").toInt())
                appDatabase?.movieFavouriteDao()?.remove(favourite)
            }
            AsyncTask.execute(delete);
        } else {
            val insert = Runnable {
                val appDatabase =
                    getInstance(applicationContext)
                val favourite =
                    Favourite(0, UserSingleton.getUser(), intent.getStringExtra("ID").toInt())
                appDatabase?.movieFavouriteDao()?.save(favourite)
                Log.i("FAVOURITE", favourite.movieId.toString())
            }
            AsyncTask.execute(insert);
            getFavourite(findViewById(R.id.buttonDetail))
        }

    }

}
