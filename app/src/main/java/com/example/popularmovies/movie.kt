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

data class movie_search(
    val id : String,
    val genres : ArrayList<Genre>,
    val poster_path: String,
    val backdrop_path: String,
    val original_title: String,
    val overview : String,
    val release_date : String,
    val tagline : String,
    val vote_average : String
)

data class Genre(
    val name : String
)

data class moviecastresopnse(
    val id:Int=0,
    val cast:ArrayList<moviecast>

)
data class moviecast(
    val cast_id:Int,
    val name: String,
    val profile_path:String
)
data class similarresonse(
    val page:Int=0,
    val results:ArrayList<similar>

)
data class similar(
    val cast_id:Int,
    val id: Int,
    val original_title: String,
    val poster_path:String
)
