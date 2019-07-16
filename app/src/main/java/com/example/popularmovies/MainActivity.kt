package com.example.popularmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    val api_key:String="0e03d86efe00ea1a1e1dd7d2a4717ba1"
    var maxLimit : Int = 996

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       val retrofit=Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
           .addConverterFactory(GsonConverterFactory.create())
           .build()
        val service=retrofit.create(popinterface::class.java)
        val call=service.getPopular(api_key,trackN.text.toString())
        call.enqueue(object : Callback<movieresponse> {
            override fun onFailure(call: Call<movieresponse>, t: Throwable) {
                Log.d("MoviesDagger", t.toString())
                 }



            override fun onResponse(call: Call<movieresponse>, response: Response<movieresponse>) {

                val data=response.body()
                val data1= data?.results




                rView.layoutManager =
                    GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)
                rView.adapter = data1?.let { movieadapter(this@MainActivity, it,false) }



            }
        })




    }
}
