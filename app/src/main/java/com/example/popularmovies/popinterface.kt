package com.example.popularmovies


import retrofit2.http.GET
import retrofit2.http.Query

interface popinterface {

    @GET("3/movie/popular")
    fun getPopular(
        @Query("api_key") key : String,
        @Query("page") page : String
    ) : retrofit2.Call<movieresponse>
}