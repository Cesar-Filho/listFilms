package com.example.listfilms

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.listfilms.model.Favourite
import com.example.listfilms.model.Movie
import com.example.listfilms.model.User
import com.example.listfilms.persistence.MainDataBase.getInstance
import com.example.listfilms.repository.MoviesRepository
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var movie: Movie
    private var favourite: Favourite? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val buttonDetail = findViewById<Button>(R.id.buttonDetail)

        byId(intent.getStringExtra("ID").toInt())
        getFavourite(buttonDetail)

        buttonDetail.setOnClickListener(this)
    }

    private fun byId(id: Int) {
        MoviesRepository.getMovie(id, ::success, ::error)
    }

    private fun getFavourite(buttonDetail: Button) {

        val appDatabase = getInstance(applicationContext)

        var user = appDatabase.userDao().byEmail(
            UserSingleton.getUser()
        )

        if (user === null) {
            appDatabase.userDao().save(User(0, UserSingleton.getUser()))
            user = appDatabase.userDao().byEmail(
                UserSingleton.getUser()
            )
        }

        val movieId = intent.getStringExtra("ID").toInt()
        favourite = appDatabase.favouriteDao().byFavourite(user.email, movieId)

        buttonDetail.text = getString(R.string.remove_favourite)
        if (favourite === null) {
            buttonDetail.text = getString(R.string.add_favourite)
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
        Toast.makeText(this, getString(R.string.invalid_parameters), Toast.LENGTH_LONG)
            .show()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.buttonDetail -> toggleFavourite()
        }
    }

    private fun toggleFavourite() {
        val appDatabase = getInstance(applicationContext)
        if (favourite !== null) {
            appDatabase.favouriteDao().remove(favourite!!)
            getFavourite(findViewById(R.id.buttonDetail))
            return
        }

        val favourite = Favourite(0, UserSingleton.getUser(), intent.getStringExtra("ID").toInt())
        appDatabase.favouriteDao().save(favourite)

        getFavourite(findViewById(R.id.buttonDetail))
    }

}
