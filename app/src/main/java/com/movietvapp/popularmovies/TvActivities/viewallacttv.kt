package com.movietvapp.popularmovies.TvActivities

import android.os.Bundle
import android.util.Log
import android.widget.AbsListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.movietvapp.popularmovies.Model.tv
import com.movietvapp.popularmovies.Model.tvresponse
import com.movietvapp.popularmovies.Network.popinterface
import com.movietvapp.popularmovies.R
import com.movietvapp.popularmovies.movieadapters.viewalladapter
import com.movietvapp.popularmovies.tvadapters.viewalladaptertv
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
    var isScrolling : Boolean = false
    var currentItems : Int = 0
    var totalItems : Int = 0
    var scrolledOutItems : Int = 0
    var currentPage : Int = 1
    var i = 0
    var count = 0
    lateinit var tvList : ArrayList<tv>
    lateinit var layoutManager: RecyclerView.LayoutManager
    private var gridLayoutManager: GridLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewallact)

        val type = intent.getStringExtra("type")
        val service=retrofit.create(popinterface::class.java)
        fun toBeCalled() {

            if (type == "Popular") {


                service.getpopulartv(api_key,currentPage.toString()).enqueue(object : Callback<tvresponse> {
                    override fun onFailure(call: Call<tvresponse>, t: Throwable) {
                        Log.d("MoviesDagger", t.toString())
                    }


                    override fun onResponse(
                        call: Call<tvresponse>,
                        response: Response<tvresponse>
                    ) {

                        val data = response.body()
                        val data1 = data!!.results

                        progressBarall.isVisible = false

                        //  rView.layoutManager =
                        //     GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)
                        if(i==0) {
                            tvList = data1
                            rViewall.layoutManager =
                                GridLayoutManager(this@viewallacttv, 2)
                            rViewall.adapter = viewalladaptertv(
                                this@viewallacttv,
                                tvList,
                                false
                            )
                        }
                        else {
                            tvList.addAll(data1)
                            rViewall.adapter!!.notifyDataSetChanged()

                        }
                        i++


                    }
                })
            } else
                if (type == "airingtoday") {


                    service.getairingtodayall(api_key,currentPage.toString()).enqueue(object : Callback<tvresponse> {
                        override fun onFailure(call: Call<tvresponse>, t: Throwable) {
                            Log.d("MoviesDagger", t.toString())
                        }


                        override fun onResponse(
                            call: Call<tvresponse>,
                            response: Response<tvresponse>
                        ) {

                            val data = response.body()
                            val data1 = data!!.results
                            progressBarall.isVisible = false


                            //  rView.layoutManager =
                            //     GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)

                            if(i==0) {
                                tvList = data1
                                rViewall.layoutManager =
                                    GridLayoutManager(this@viewallacttv, 2)
                                rViewall.adapter = viewalladaptertv(
                                    this@viewallacttv,
                                    tvList,
                                    false
                                )
                            }
                            else {
                                tvList.addAll(data1)
                                rViewall.adapter!!.notifyDataSetChanged()

                            }
                            i++



                        }
                    })
                } else
                    if (type == "Toprated") {


                        service.gettopratedtv(api_key,currentPage.toString()).enqueue(object : Callback<tvresponse> {
                            override fun onFailure(call: Call<tvresponse>, t: Throwable) {
                                Log.d("MoviesDagger", t.toString())
                            }


                            override fun onResponse(
                                call: Call<tvresponse>,
                                response: Response<tvresponse>
                            ) {

                                val data = response.body()
                                val data1 = data!!.results
                                progressBarall.isVisible = false


                                //  rView.layoutManager =
                                //     GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)

                                if(i==0) {
                                    tvList = data1
                                    rViewall.layoutManager =
                                        GridLayoutManager(this@viewallacttv, 2)
                                    rViewall.adapter = viewalladaptertv(
                                        this@viewallacttv,
                                        tvList,
                                        false
                                    )
                                }
                                else {
                                    tvList.addAll(data1)
                                    rViewall.adapter!!.notifyDataSetChanged()

                                }
                                i++



                            }
                        })
                    } else
                        if (type == "ontheair") {


                            service.getontheair(api_key,currentPage.toString()).enqueue(object : Callback<tvresponse> {
                                override fun onFailure(call: Call<tvresponse>, t: Throwable) {
                                    Log.d("MoviesDagger", t.toString())
                                }


                                override fun onResponse(
                                    call: Call<tvresponse>,
                                    response: Response<tvresponse>
                                ) {

                                    val data = response.body()
                                    val data1 = data!!.results
                                    progressBarall.isVisible = false


                                    //  rView.layoutManager =
                                    //     GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)

                                    if(i==0) {
                                        tvList = data1
                                        rViewall.layoutManager =
                                            GridLayoutManager(this@viewallacttv, 2)
                                        rViewall.adapter = viewalladaptertv(
                                            this@viewallacttv,
                                            tvList,
                                            false
                                        )
                                    }
                                    else {
                                        tvList.addAll(data1)
                                        rViewall.adapter!!.notifyDataSetChanged()

                                    }
                                    i++



                                }
                            })
                        }
        }
        toBeCalled()

        rViewall.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                layoutManager = rViewall.layoutManager!!
                currentItems = layoutManager.childCount
                totalItems = layoutManager.itemCount
                when (layoutManager) {
                    is GridLayoutManager -> gridLayoutManager = layoutManager as GridLayoutManager
                }
                scrolledOutItems = gridLayoutManager!!.findFirstVisibleItemPosition()

                if ((scrolledOutItems + currentItems == totalItems) && isScrolling) {
                    currentPage++
                    isScrolling = false
                    toBeCalled()
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }
        })

    }

}