package com.example.popularmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.popularmovies.Network.popinterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivitytv2 : AppCompatActivity() {
    val api_key:String="0e03d86efe00ea1a1e1dd7d2a4717ba1"
    var maxLimit : Int =996
    val retrofit= Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val baseURL = "https://image.tmdb.org/t/p/w780/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_4)
        val id = intent.getStringExtra("id").toInt()

        val type = intent.getStringExtra("type")
        val service=retrofit.create(popinterface::class.java)
    }
}
