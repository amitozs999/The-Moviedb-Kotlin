package com.example.popularmovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.popularmovies.MovieActivites.MainActivity
import com.example.popularmovies.TvActivities.MainActivitytv
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivityPeople : AppCompatActivity() {
    val api_key:String="0e03d86efe00ea1a1e1dd7d2a4717ba1"
    var maxLimit : Int =996
    val retrofit= Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    lateinit var toolbar: android.app.ActionBar
    var language:String="en"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_5)
        var toolbar = supportActionBar
        val naview=findViewById<View>(R.id.nav) as? BottomNavigationView
        var menu = naview?.menu
        var menuItem = menu?.getItem(1)
        menuItem?.isChecked = true
        naview?.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.movies -> {
                    val  intent1= Intent(this, MainActivity::class.java)
                    startActivity(intent1)
                    return@setOnNavigationItemSelectedListener  true
                }
                R.id.person -> {
                    val  intent3=Intent(this, MainActivityPeople::class.java)
                    startActivity(intent3)
                    return@setOnNavigationItemSelectedListener  true
                }
                R.id.tv -> {
                    val  intent2=Intent(this, MainActivitytv::class.java)
                    startActivity(intent2)
                    return@setOnNavigationItemSelectedListener  true
                }

                else-> return@setOnNavigationItemSelectedListener  true


            }

        }
    }
}
