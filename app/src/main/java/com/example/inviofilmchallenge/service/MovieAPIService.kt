package com.example.inviofilmchallenge.service

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MovieAPIService {
     val BASE_URL ="https://omdbapi.com/"
    // https://www.omdbapi.com/?t=batman&apikey=4a2a1753
    fun getMovieApi():MovieAPI{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(MovieAPI::class.java)
    }
    fun getDenemeApi():MovieAPI{
        return Retrofit.Builder()
            .baseUrl("http://matematikapp.xyz/matematikapp/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(MovieAPI::class.java)
    }
}