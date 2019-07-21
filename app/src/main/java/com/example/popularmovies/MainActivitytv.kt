package com.example.popularmovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.popularmovies.MovieActivites.MainActivity
import com.example.popularmovies.Network.popinterface
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivitytv : AppCompatActivity() {
    val api_key:String="0e03d86efe00ea1a1e1dd7d2a4717ba1"
    var maxLimit : Int =996
    val retrofit= Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    lateinit var toolbar: android.app.ActionBar

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


    }
    start()
}
fun start()
{


    val service=retrofit.create(popinterface::class.java)
}