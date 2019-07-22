package com.example.popularmovies.TvActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmovies.MainActivityPeople
import com.example.popularmovies.Model.tvresponse
import com.example.popularmovies.MovieActivites.MainActivity
import com.example.popularmovies.Network.popinterface
import com.example.popularmovies.R
import com.example.popularmovies.tvadapters.tvadapter
import com.example.popularmovies.tvadapters.tvadaptercommon
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_3.*
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
                R.id.tv -> {
                    val  intent2=Intent(this, MainActivitytv::class.java)
                    startActivity(intent2)
                    return@setOnNavigationItemSelectedListener  true
                }

                R.id.person -> {
                    val  intent3=Intent(this, MainActivityPeople::class.java)
                    startActivity(intent3)
                    return@setOnNavigationItemSelectedListener  true
                }

                else-> return@setOnNavigationItemSelectedListener  true


            }

        }
         start()

    }
    override fun onBackPressed() {
        val i=Intent(this,MainActivity::class.java)
        startActivity(i)
        finishAffinity()
    }
    fun start()
    {


        val service=retrofit.create(popinterface::class.java)

        text10tv.setOnClickListener {


            val intent= Intent(this, viewallacttv::class.java)

            intent.putExtra("type","Popular")
            startActivity(intent,null)

        }


        text00tv.setOnClickListener {


            val intent= Intent(this, viewallacttv::class.java)

            intent.putExtra("type","airingtoday")
            startActivity(intent,null)


        }
        text20tv.setOnClickListener {


            val intent= Intent(this, viewallacttv::class.java)

            intent.putExtra("type","Toprated")
            startActivity(intent,null)


        }

        text30tv.setOnClickListener {


            val intent= Intent(this, viewallacttv::class.java)

            intent.putExtra("type","ontheair")
            startActivity(intent,null)


        }

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
