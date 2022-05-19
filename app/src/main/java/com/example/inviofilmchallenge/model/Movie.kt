package com.example.inviofilmchallenge.model

import com.google.gson.annotations.SerializedName
import java.util.*


//Movie sınıfı oluşturuldu bu sınıf ile veri çekilip çekilen veri sayfalar arası taşınacak

data class Movie(

    @SerializedName("Title")
    var title: String?,
    @SerializedName("Year")
    var year: String?,
    @SerializedName("Rated")
    var rated: String?,
    @SerializedName("Released")
    var released: String?,
    @SerializedName("Genre")
    var genre: String?,
    @SerializedName("Runtime")
    var runtime: String?,
    @SerializedName("Director")
    var director: String?,
    @SerializedName("Writer")
    var writer: String?,
    @SerializedName("Actors")
    var actors: String?,
    @SerializedName("Plot")
    var plot: String?,
    @SerializedName("Language")
    var language: String?,
    @SerializedName("Country")
    var country: String?,
    @SerializedName("Awards")
    var awards: String?,
    @SerializedName("Poster")
    var poster: String?,
    @SerializedName("Ratings")
    var ratings: ArrayList<Rating>?,
    @SerializedName("Metascore")
    var metascore: String?,
    @SerializedName("imdbRating")
    var imdbRating: String?,
    @SerializedName("imdbID")
    var imdbID: String?,
    @SerializedName("Type")
    var type: String?,
    @SerializedName("DVD")
    var dVD: String?,
    @SerializedName("BoxOffice")
    var boxOffice: String?,
    @SerializedName("Production")
    var production: String?,
    @SerializedName("Website")
    var website: String?,
    @SerializedName("Response")
    var response: String?

/*
    @SerializedName("Title")
    var title: String?,
    @SerializedName("Year")
    var year: String?,
    @SerializedName("imdbID")
    var imdbID: String?,
    @SerializedName("Type")
    var type: String?,
    @SerializedName("Poster")
    var poster: String?
    */
)