package com.example.popularmovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.popularmovies.Model.movie_search
import com.example.popularmovies.Model.movieresponse
import com.example.popularmovies.MovieActivites.MainActivity
import com.example.popularmovies.MovieActivites.activity_second
import com.example.popularmovies.Network.popinterface
import com.example.popularmovies.PeopleActivities.MainActivityPeople
import com.example.popularmovies.TvActivities.MainActivitytv
import com.example.popularmovies.movieadapters.movieadapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_7.*
import kotlinx.android.synthetic.main.activity_second.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivityFavourite : AppCompatActivity() {

    val api_key:String="0e03d86efe00ea1a1e1dd7d2a4717ba1"
    var maxLimit : Int =996
    val retrofit= Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val baseURL = "https://image.tmdb.org/t/p/w780/"
    val service=retrofit.create(popinterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_7)
        val naview=findViewById<View>(R.id.nav) as BottomNavigationView
        var menu = naview.menu
        var menuItem = menu.getItem(3)
        menuItem.isChecked = true

        naview.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.movies -> {
                    val  intent1= Intent(this, MainActivity::class.java)
                    startActivity(intent1)
                    return@setOnNavigationItemSelectedListener  true
                }
                R.id.tv -> {
                    val  intent2= Intent(this, MainActivitytv::class.java)
                    startActivity(intent2)
                    return@setOnNavigationItemSelectedListener  true
                }
                R.id.person -> {
                    val  intent3= Intent(this, MainActivityPeople::class.java)
                    startActivity(intent3)
                    return@setOnNavigationItemSelectedListener  true
                }
                R.id.FAV -> {
                    val  intent4= Intent(this, MainActivityFavourite::class.java)
                    startActivity(intent4)
                    return@setOnNavigationItemSelectedListener  true
                }

                else-> return@setOnNavigationItemSelectedListener  true


            }

        }


        val db: FavouriteDatabase by lazy {
            Room.databaseBuilder(
                this,
                FavouriteDatabase::class.java,
                "Fav.db"
            ).allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }

        //var db=FavouriteDatabase.getfavouriteDatabase(this)
       var a= db.FavDao().getallfav()
        Log.d("AMITOZ",a.size.toString())

        for(i in 1 until a.size)
        {
            var id =a[i].movie_id.toInt()
            service.getmovies(id,api_key).enqueue(object : Callback<movie_search> {
                override fun onFailure(call: Call<movie_search>, t: Throwable) {
                    Log.d("MoviesDagger", t.toString())
                }




                override fun onResponse(call: Call<movie_search>, response: Response<movie_search>) {

                    val data=response.body()
                    Log.d("gggg",data!!.original_title)



                    //  rView.layoutManager =
                    //     GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)

                    rvfav.layoutManager =
                        GridLayoutManager(this@MainActivityFavourite,2, RecyclerView.VERTICAL,false)
                    rvfav.adapter = data?.let {
                        favouriteAdapter(
                            this@MainActivityFavourite,
                            it,
                            false
                        )
                    }



                }
            })


        }
    }
    override fun onBackPressed() {
        val i=Intent(this,MainActivity::class.java)
        startActivity(i)
        finishAffinity()
    }

}
