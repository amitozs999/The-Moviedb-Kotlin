package com.example.popularmovies.MovieActivites


import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmovies.MainActivityPeople
import com.example.popularmovies.Model.Searchresonse

import com.example.popularmovies.TvActivities.MainActivitytv
import com.example.popularmovies.Model.movieresponse
import com.example.popularmovies.Network.popinterface
import com.example.popularmovies.R
import com.example.popularmovies.SearchActivity
import com.example.popularmovies.SearchAdapter
import com.example.popularmovies.movieadapters.movieadapter
import com.example.popularmovies.movieadapters.upmovieadapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.seachlayout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    val api_key:String="0e03d86efe00ea1a1e1dd7d2a4717ba1"
    var maxLimit : Int =996
    val retrofit=Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service=retrofit.create(popinterface::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val naview=findViewById<View>(R.id.nav) as BottomNavigationView
         var menu = naview.menu
        var menuItem = menu.getItem(0)
        menuItem.isChecked = true

        naview.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.movies -> {
                    val  intent1=Intent(this, MainActivity::class.java)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater=menuInflater
        inflater.inflate(R.menu.search_menu,menu)

        val manager=getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchitem=menu?.findItem(R.id.searchid)
        val searchview=searchitem?.actionView as SearchView
        searchview.setSearchableInfo(manager.getSearchableInfo(componentName))
        searchview.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                searchview.clearFocus()
                val intent=Intent(this@MainActivity,SearchActivity::class.java)
                intent.putExtra("text", query)
                startActivity(intent)


                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {




                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.searchid->{
                return true
            }
            else-> super.onOptionsItemSelected(item)
        }
        return true
    }


    override fun onBackPressed() {
       finish()
    }

    fun start()
    {


            text10.setOnClickListener {


                val intent= Intent(this, viewallact::class.java)

                intent.putExtra("type","Popular")
               startActivity(intent,null)

            }


        text00.setOnClickListener {


            val intent= Intent(this, viewallact::class.java)

            intent.putExtra("type","Nowplaying")
            startActivity(intent,null)


        }
        text20.setOnClickListener {


            val intent= Intent(this, viewallact::class.java)

            intent.putExtra("type","Toprated")
            startActivity(intent,null)


        }

        text30.setOnClickListener {


            val intent= Intent(this, viewallact::class.java)

            intent.putExtra("type","Upcoming")
            startActivity(intent,null)


        }





            service.getPopular(api_key).enqueue(object : Callback<movieresponse> {
            override fun onFailure(call: Call<movieresponse>, t: Throwable) {
                Log.d("MoviesDagger", t.toString())
            }



            override fun onResponse(call: Call<movieresponse>, response: Response<movieresponse>) {

                val data=response.body()
                val data1= data?.results


                //  rView.layoutManager =
                //     GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)

                rView.layoutManager =
                    LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL,false)
                rView.adapter = data1?.let {
                    movieadapter(
                        this@MainActivity,
                        it,
                        false
                    )
                }



            }
        })


        service.getToprated(api_key).enqueue(object : Callback<movieresponse> {
            override fun onFailure(call: Call<movieresponse>, t: Throwable) {
                Log.d("MoviesDagger", t.toString())
            }



            override fun onResponse(call: Call<movieresponse>, response: Response<movieresponse>) {

                val data=response.body()
                val data1= data?.results


                //  rView.layoutManager =
                //     GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)

                rView2.layoutManager =
                    LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL,false)
                rView2.adapter = data1?.let {
                    movieadapter(
                        this@MainActivity,
                        it,
                        false
                    )
                }



            }
        })


        service.getUpcoming(api_key).enqueue(object : Callback<movieresponse> {
            override fun onFailure(call: Call<movieresponse>, t: Throwable) {
                Log.d("MoviesDagger", t.toString())
            }



            override fun onResponse(call: Call<movieresponse>, response: Response<movieresponse>) {

                val data=response.body()
                val data1= data?.results


                //  rView.layoutManager =
                //     GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)

                rView3.layoutManager =
                    LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL,false)
                rView3.adapter = data1?.let {
                    movieadapter(
                        this@MainActivity,
                        it,
                        false
                    )
                }



            }
        })


        service.getNowplaying(api_key).enqueue(object : Callback<movieresponse> {



            override fun onFailure(call: Call<movieresponse>, t: Throwable) {
                Log.d("MoviesDagger", t.toString())
            }



            override fun onResponse(call: Call<movieresponse>, response: Response<movieresponse>) {

                val data=response.body()
                val data1= data?.results


                //  rView.layoutManager =
                //     GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)

                rView0.layoutManager =
                    LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL,false)
                rView0.adapter = data1?.let {
                    upmovieadapter(
                        this@MainActivity,
                        it,
                        false
                    )
                }



            }
        })
    }
}
