package com.example.popularmovies


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface popinterface {

    @GET("3/movie/popular")
    fun getPopular(
        @Query("api_key") key : String
       // @Query("page") page : String
    ) : Call<movieresponse>

    @GET("3/movie/top_rated")
    fun getToprated(

        @Query("api_key") key:String
        // @Query("page") page : String
    ) : Call<movieresponse>

    @GET("3/movie/upcoming")
    fun getUpcoming(

        @Query("api_key") key:String
        // @Query("page") page : String
    ) : Call<movieresponse>

    @GET("3/movie/now_playing")
    fun getNowplaying(

        @Query("api_key") key:String
        // @Query("page") page : String
    ) : Call<movieresponse>







}
