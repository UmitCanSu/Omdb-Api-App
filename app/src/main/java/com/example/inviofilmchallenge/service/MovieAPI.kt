package com.example.inviofilmchallenge.service

import com.example.inviofilmchallenge.model.Movie
import com.example.inviofilmchallenge.model.Search
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieAPI {



     @GET("?")
    fun getSearchMovie(@Query("s") movieName :String,@Query("apikey") apiKey:String ="4a2a1753"): Single<Search>
    @GET("?")
    fun getFindImdbMovie(@Query("i") movieName :String,@Query("apikey") apiKey:String ="4a2a1753"): Single<Movie>

    /*
    @GET("?s=batman&apikey=4a2a1753")
    fun getSearchMovie(): Single<Search>
*/




}