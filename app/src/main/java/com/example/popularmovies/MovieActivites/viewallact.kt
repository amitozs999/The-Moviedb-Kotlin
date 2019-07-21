package com.example.popularmovies.MovieActivites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.popularmovies.Model.movieresponse
import com.example.popularmovies.Network.popinterface
import com.example.popularmovies.R
import com.example.popularmovies.movieadapters.viewalladapter
import kotlinx.android.synthetic.main.activity_viewallact.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class viewallact : AppCompatActivity() {
    val api_key:String="0e03d86efe00ea1a1e1dd7d2a4717ba1"
    var maxLimit : Int =996
    val retrofit= Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewallact)

        val type = intent.getStringExtra("type")
        val service=retrofit.create(popinterface::class.java)

        if(type=="Popular") {


            service.getPopular(api_key).enqueue(object : Callback<movieresponse> {
                override fun onFailure(call: Call<movieresponse>, t: Throwable) {
                    Log.d("MoviesDagger", t.toString())
                }



                override fun onResponse(call: Call<movieresponse>, response: Response<movieresponse>) {

                    val data=response.body()
                    val data1= data?.results


                    //  rView.layoutManager =
                    //     GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)

                    rViewall.layoutManager =
                        GridLayoutManager(this@viewallact, 2)
                    rViewall.adapter = data1?.let {
                        viewalladapter(
                            this@viewallact,
                            it,
                            false
                        )
                    }



                }
            })
        }
        else
        if(type=="Nowplaying") {


            service.getNowplaying(api_key).enqueue(object : Callback<movieresponse> {
                override fun onFailure(call: Call<movieresponse>, t: Throwable) {
                    Log.d("MoviesDagger", t.toString())
                }



                override fun onResponse(call: Call<movieresponse>, response: Response<movieresponse>) {

                    val data=response.body()
                    val data1= data?.results


                    //  rView.layoutManager =
                    //     GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)

                    rViewall.layoutManager =
                        GridLayoutManager(this@viewallact, 2)
                    rViewall.adapter = data1?.let {
                        viewalladapter(
                            this@viewallact,
                            it,
                            false
                        )
                    }



                }
            })
        }
        else
        if(type=="Toprated") {


            service.getToprated(api_key).enqueue(object : Callback<movieresponse> {
                override fun onFailure(call: Call<movieresponse>, t: Throwable) {
                    Log.d("MoviesDagger", t.toString())
                }



                override fun onResponse(call: Call<movieresponse>, response: Response<movieresponse>) {

                    val data=response.body()
                    val data1= data?.results


                    //  rView.layoutManager =
                    //     GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)

                    rViewall.layoutManager =
                        GridLayoutManager(this@viewallact, 2)
                    rViewall.adapter = data1?.let {
                        viewalladapter(
                            this@viewallact,
                            it,
                            false
                        )
                    }



                }
            })
        }
        else
        if(type=="Upcoming") {


            service.getUpcoming(api_key).enqueue(object : Callback<movieresponse> {
                override fun onFailure(call: Call<movieresponse>, t: Throwable) {
                    Log.d("MoviesDagger", t.toString())
                }



                override fun onResponse(call: Call<movieresponse>, response: Response<movieresponse>) {

                    val data=response.body()
                    val data1= data?.results


                    //  rView.layoutManager =
                    //     GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)

                    rViewall.layoutManager =
                        GridLayoutManager(this@viewallact, 2)
                    rViewall.adapter = data1?.let {
                        viewalladapter(
                            this@viewallact,
                            it,
                            false
                        )
                    }



                }
            })
        }

    }

}
