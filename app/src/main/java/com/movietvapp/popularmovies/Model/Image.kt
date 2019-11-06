package com.movietvapp.popularmovies.Model

data class imageresponse(
    val id : Int,
    val backdrops : List<image1>,
    val posters : List<image2>
)

data class image1(
   var height:Int,
   var width:Int
)
data class image2(
    var height:Int,
    var width:Int
)
