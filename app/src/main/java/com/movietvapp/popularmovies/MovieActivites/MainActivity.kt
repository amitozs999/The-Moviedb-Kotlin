package com.movietvapp.popularmovies.MovieActivites


import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.movietvapp.popularmovies.MainActivityFavourite
import com.movietvapp.popularmovies.PeopleActivities.MainActivityPeople

import com.movietvapp.popularmovies.TvActivities.MainActivitytv
import com.movietvapp.popularmovies.Model.movieresponse
import com.movietvapp.popularmovies.Network.popinterface
import com.movietvapp.popularmovies.R
import com.movietvapp.popularmovies.SearchActivities.SearchActivity
import com.movietvapp.popularmovies.movieadapters.movieadapter
import com.movietvapp.popularmovies.movieadapters.upmovieadapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
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

        text0.isVisible=false
        text1.isVisible=false
        text2.isVisible=false
        text3.isVisible=false

        text00.isVisible=false
        text10.isVisible=false
        text20.isVisible=false
        text30.isVisible=false



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
                R.id.FAV -> {
                    val  intent4= Intent(this, MainActivityFavourite::class.java)
                    startActivity(intent4)
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

        var manager=getSystemService(Context.SEARCH_SERVICE) as SearchManager
        var searchitem=menu?.findItem(R.id.searchid)
        var searchview=searchitem?.actionView as SearchView
        searchview.setSearchableInfo(manager.getSearchableInfo(componentName))
        searchview.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                //searchview.clearFocus()
//                searchview.setQuery(" ",false)
                searchview.queryHint="Search Movies Here..."



//                searchview.setIconifiedByDefault(false)
                //searchview.isIconified=false
                val intent=Intent(this@MainActivity, SearchActivity::class.java)
                intent.putExtra("text", query)
                intent.putExtra("type","movie")
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

                text1.isVisible=true
                text10.isVisible=true
                progressBar.isVisible=false



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
                text20.isVisible=true
                text2.isVisible=true
                progressBar.isVisible=false


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
                text3.isVisible=true
                text30.isVisible=true
                progressBar.isVisible=false


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
                text0.isVisible=true
                text00.isVisible=true
                progressBar.isVisible=false


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
