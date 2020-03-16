package com.example.listfilms.api

import com.example.listfilms.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface Api {
    @GET("discover/movie/")
    fun getMovies(
        @Query("api_key") apiKey: String? = "0fd39ee658836165134c9eefe5ebab41",
        @Query("page") page: Int? = 1,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("language") language: String = "pt-BR"
    ): Call<GetMoviesResponse>

    @GET("movie/{movie_id}")
    fun getMovie(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") apiKey: String? = "0fd39ee658836165134c9eefe5ebab41",
        @Query("language") language: String = "pt-BR"
    ): Call<Movie>
}