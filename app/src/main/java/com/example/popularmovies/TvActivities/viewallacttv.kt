package com.example.popularmovies.TvActivities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.popularmovies.Model.tvresponse
import com.example.popularmovies.Network.popinterface
import com.example.popularmovies.R
import com.example.popularmovies.tvadapters.viewalladaptertv
import kotlinx.android.synthetic.main.activity_viewallact.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class viewallacttv : AppCompatActivity() {
    var language="en-US"
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


            service.getpopulartv(api_key).enqueue(object : Callback<tvresponse> {
                override fun onFailure(call: Call<tvresponse>, t: Throwable) {
                    Log.d("MoviesDagger", t.toString())
                }



                override fun onResponse(call: Call<tvresponse>, response: Response<tvresponse>) {

                    val data=response.body()
                    val data1= data?.results


                    //  rView.layoutManager =
                    //     GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)

                    rViewall.layoutManager =
                        GridLayoutManager(this@viewallacttv, 2)
                    rViewall.adapter = data1?.let {
                        viewalladaptertv(
                            this@viewallacttv,
                            it,
                            false
                        )
                    }



                }
            })
        }
        else
            if(type=="airingtoday") {


                service.getairingtodayall(api_key).enqueue(object : Callback<tvresponse> {
                    override fun onFailure(call: Call<tvresponse>, t: Throwable) {
                        Log.d("MoviesDagger", t.toString())
                    }



                    override fun onResponse(call: Call<tvresponse>, response: Response<tvresponse>) {

                        val data=response.body()
                        val data1= data?.results


                        //  rView.layoutManager =
                        //     GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)

                        rViewall.layoutManager =
                            GridLayoutManager(this@viewallacttv, 2)
                        rViewall.adapter = data1?.let {
                            viewalladaptertv(
                                this@viewallacttv,
                                it,
                                false
                            )
                        }



                    }
                })
            }
            else
                if(type=="Toprated") {


                    service.gettopratedtv(api_key).enqueue(object : Callback<tvresponse> {
                        override fun onFailure(call: Call<tvresponse>, t: Throwable) {
                            Log.d("MoviesDagger", t.toString())
                        }



                        override fun onResponse(call: Call<tvresponse>, response: Response<tvresponse>) {

                            val data=response.body()
                            val data1= data?.results


                            //  rView.layoutManager =
                            //     GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)

                            rViewall.layoutManager =
                                GridLayoutManager(this@viewallacttv, 2)
                            rViewall.adapter = data1?.let {
                                viewalladaptertv(
                                    this@viewallacttv,
                                    it,
                                    false
                                )
                            }



                        }
                    })
                }
                else
                    if(type=="ontheair") {


                        service.getontheair(api_key).enqueue(object : Callback<tvresponse> {
                            override fun onFailure(call: Call<tvresponse>, t: Throwable) {
                                Log.d("MoviesDagger", t.toString())
                            }



                            override fun onResponse(call: Call<tvresponse>, response: Response<tvresponse>) {

                                val data=response.body()
                                val data1= data?.results


                                //  rView.layoutManager =
                                //     GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)

                                rViewall.layoutManager =
                                    GridLayoutManager(this@viewallacttv, 2)
                                rViewall.adapter = data1?.let {
                                    viewalladaptertv(
                                        this@viewallacttv,
                                        it,
                                        false
                                    )
                                }



                            }
                        })
                    }

    }

}