package com.example.inviofilmchallenge.model

import com.google.gson.annotations.SerializedName

data class Search(

    @SerializedName("Search")
   var  search:List<Movie>,
    @SerializedName("Response")
    var response: String?,
    @SerializedName("Error")
    var error: String?
)