package com.example.popularmovies.Network


import com.example.popularmovies.Favourite
import com.example.popularmovies.Model.*
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

    @GET("3/movie/{id}")
    fun getfav(

        @Path("id") id:Int,
        @Query("api_key") key: String
    ) :Call<List<Favourite>>

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




    @GET("3/tv/airing_today")
    fun getairingtoday(
        @Query("api_key") key : String,
        @Query("language") language:String
        // @Query("page") page : String
    ) : Call<tvresponse>

    @GET("3/tv/airing_today")
    fun getairingtodayall(
        @Query("api_key") key : String

        // @Query("page") page : String
    ) : Call<tvresponse>


    @GET("3/tv/popular")
    fun getpopulartv(
        @Query("api_key") key : String
        // @Query("page") page : String
    ) : Call<tvresponse>

    @GET("3/tv/top_rated")
    fun gettopratedtv(
        @Query("api_key") key : String
        // @Query("page") page : String
    ) : Call<tvresponse>

    @GET("3/tv/on_the_air")
    fun getontheair(
        @Query("api_key") key : String
        // @Query("page") page : String
    ) : Call<tvresponse>




    @GET("3/tv/{tv_id}}")
    fun gettv(

        @Path("tv_id") id:Int,
        @Query("api_key") key: String
    ) :Call<tv>

    @GET("3/tv/{id}/credits")
    fun gettvcast(

        @Path("id") id:Int,
        @Query("api_key") key: String
    ) :Call<tvcastresponse>

    @GET("3/tv/{tv_id}/similar")
    fun getsimilartv(

        @Path("tv_id") tvid:Int,
        @Query("api_key") key: String
    ):Call<tvresponse>

    @GET("3/tv/{id}/videos")
    fun getvideostv(
        @Path("id") id:Int,
        @Query("api_key") key: String

    ):Call<videoresponsetv>



    @GET("3/person/popular")
    fun getPopularpeople(
        @Query("api_key") key : String
        // @Query("page") page : String
    ) : Call<peopleresponse>


    @GET("3/person/{id}")
    fun getpeopledetail(
        @Path("id") id: Number,
        @Query("api_key") key : String
        // @Query("page") page : String
    ) : Call<people>


    @GET("3/search/person")
    fun getSearchPeople(
        @Query("api_key") key : String,
        @Query("query") query : String
    ) : Call<Searchresonse>

    @GET("3/search/movie")
    fun getSearchMovie(
        @Query("api_key") key : String,
        @Query("query") query : String
    ) : Call<Searchresonse>

    @GET("3/search/tv")
    fun getSearchTv(
        @Query("api_key") key : String,
        @Query("query") query : String
    ) : Call<Searchresonse>


}
