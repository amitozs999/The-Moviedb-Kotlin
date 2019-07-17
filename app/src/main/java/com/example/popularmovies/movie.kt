package com.example.popularmovies

data class movieresponse(
    val page : String,
    val total_pages : String,
    val results : ArrayList<movie>
)

data class movie(
    var id : String,
    var original_title : String,
    var poster_path : String,
    var backdrop_path:String
)
