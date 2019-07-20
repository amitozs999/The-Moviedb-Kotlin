package com.example.popularmovies


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("3/movie/{movie_id}/images")
    fun getimage(
        @Path("id") id:String,
        @Query("api_key") key:String
        // @Query("page") page : String
    ) : Call<movieresponse>

    @GET("3/movie/{id}")
    fun getmovies(

        @Path("id") id:Int,
        @Query("api_key") key: String
    ) :Call<movie_search>

    @GET("3/movie/{movie_id}/credits")
    fun getcast(

        @Path("movie_id") movieid:Int,
        @Query("api_key") key: String
    ):Call<moviecastresopnse>

    @GET("3/movie/{movie_id}/similar")
    fun getsimilar(

        @Path("movie_id") movieid:Int,
        @Query("api_key") key: String
    ):Call<movieresponse>

    @GET("3/movie/{id}/videos")
    fun getvideos(
        @Path("id") id:Int,
        @Query("api_key") key: String

    ):Call<videoresponse>





}
