package com.example.popularmovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmovies.Model.movieresponse
import com.example.popularmovies.Model.tvresponse
import com.example.popularmovies.MovieActivites.MainActivity
import com.example.popularmovies.Network.popinterface
import com.example.popularmovies.movieadapters.movieadapter
import com.example.popularmovies.tvadapters.tvadapter
import com.example.popularmovies.tvadapters.tvadaptercommon
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_3.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.rView3
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivitytv : AppCompatActivity() {
    val api_key:String="0e03d86efe00ea1a1e1dd7d2a4717ba1"
    var maxLimit : Int =996
    val retrofit= Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    lateinit var toolbar: android.app.ActionBar
    var language:String="en"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)
        var toolbar = supportActionBar
        val naview=findViewById<View>(R.id.nav) as BottomNavigationView
        var menu = naview.menu
        var menuItem = menu.getItem(1)
        menuItem.isChecked = true
        naview.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.movies -> {
                    val  intent1= Intent(this, MainActivity::class.java)
                    startActivity(intent1)
                    return@setOnNavigationItemSelectedListener  true
                }

                else-> return@setOnNavigationItemSelectedListener  true


            }

        }
         start()

    }
    fun start()
    {


        val service=retrofit.create(popinterface::class.java)

        service.getairingtoday(api_key,language).enqueue(object : Callback<tvresponse> {
            override fun onFailure(call: Call<tvresponse>, t: Throwable) {
                Log.d("MoviesDagger", t.toString())
            }



            override fun onResponse(call: Call<tvresponse>, response: Response<tvresponse>) {

                val data=response.body()
                val data1= data?.results


                //  rView.layoutManager =
                //     GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)

                rViewtv1.layoutManager =
                    LinearLayoutManager(this@MainActivitytv, RecyclerView.HORIZONTAL,false)
                rViewtv1.adapter = data1?.let {
                    tvadapter(
                        this@MainActivitytv,
                        it,
                        false
                    )
                }



            }
        })

        service.getpopulartv(api_key).enqueue(object : Callback<tvresponse> {
            override fun onFailure(call: Call<tvresponse>, t: Throwable) {
                Log.d("MoviesDagger", t.toString())
            }



            override fun onResponse(call: Call<tvresponse>, response: Response<tvresponse>) {

                val data=response.body()
                val data1= data?.results


                //  rView.layoutManager =
                //     GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)

                rViewtv2.layoutManager =
                    LinearLayoutManager(this@MainActivitytv, RecyclerView.HORIZONTAL,false)
                rViewtv2.adapter = data1?.let {
                    tvadaptercommon(
                        this@MainActivitytv,
                        it,
                        false
                    )
                }



            }
        })

        service.gettopratedtv(api_key).enqueue(object : Callback<tvresponse> {
            override fun onFailure(call: Call<tvresponse>, t: Throwable) {
                Log.d("MoviesDagger", t.toString())
            }



            override fun onResponse(call: Call<tvresponse>, response: Response<tvresponse>) {

                val data=response.body()
                val data1= data?.results


                //  rView.layoutManager =
                //     GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)

                rViewtv3.layoutManager =
                    LinearLayoutManager(this@MainActivitytv, RecyclerView.HORIZONTAL,false)
                rViewtv3.adapter = data1?.let {
                    tvadaptercommon(
                        this@MainActivitytv,
                        it,
                        false
                    )
                }



            }
        })

        service.getontheair(api_key).enqueue(object : Callback<tvresponse> {
            override fun onFailure(call: Call<tvresponse>, t: Throwable) {
                Log.d("MoviesDagger", t.toString())
            }



            override fun onResponse(call: Call<tvresponse>, response: Response<tvresponse>) {

                val data=response.body()
                val data1= data?.results


                //  rView.layoutManager =
                //     GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)

                rViewtv4.layoutManager =
                    LinearLayoutManager(this@MainActivitytv, RecyclerView.HORIZONTAL,false)
                rViewtv4.adapter = data1?.let {
                    tvadaptercommon(
                        this@MainActivitytv,
                        it,
                        false
                    )
                }



            }
        })
    }
}
