package com.example.popularmovies.Model

data class tvresponse(
    val page : String,

    val results : ArrayList<tv>
)

data class tv(
    var id : String,
    var original_name : String,
    var poster_path : String,
    var backdrop_path:String,
    val overview : String,
    val release_date : String,
    val vote_average : String
)
