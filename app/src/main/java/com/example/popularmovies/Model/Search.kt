package com.example.popularmovies.Model

data class Searchresonse(
    val results : ArrayList<Search>
)

data class Search(

    val profile_path : String,
    val poster_path : String,
    val name : String,
    val original_title : String,
    val media_type : String,
    val id : String,
    var original_name: String
)