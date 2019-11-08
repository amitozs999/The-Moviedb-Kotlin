package com.movietvapp.popularmovies.PeopleActivities

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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.movietvapp.popularmovies.Model.peopleresponse
import com.movietvapp.popularmovies.MovieActivites.MainActivity
import com.movietvapp.popularmovies.Network.popinterface
import com.movietvapp.popularmovies.R
import com.movietvapp.popularmovies.SearchActivities.SearchActivity
import com.movietvapp.popularmovies.TvActivities.MainActivitytv
import com.movietvapp.popularmovies.PeopleAdapter.popularpeopleadapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.movietvapp.popularmovies.MainActivityFavourite
import kotlinx.android.synthetic.main.activity_5.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivityPeople : AppCompatActivity() {
    val api_key:String="0e03d86efe00ea1a1e1dd7d2a4717ba1"
    var maxLimit : Int =996
    val retrofit= Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service=retrofit.create(popinterface::class.java)
    var language:String="en"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_5)

         textpeople.isVisible=false


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val naview=findViewById<View>(R.id.nav) as? BottomNavigationView
        var menu = naview?.menu
        var menuItem = menu?.getItem(2)
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
        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                //searchview.clearFocus()
//                searchview.setQuery(" ",false)
                searchview.queryHint="Search People Here..."



//                searchview.setIconifiedByDefault(false)
                //searchview.isIconified=false
                val intent=Intent(this@MainActivityPeople, SearchActivity::class.java)
                intent.putExtra("text", query)
                intent.putExtra("type","people")
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
            R.id.searchid ->{
                return true
            }
            else-> super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onBackPressed() {
        val i=Intent(this,MainActivity::class.java)
        startActivity(i)
        finishAffinity()
    }

    fun start()
    {
        val service=retrofit.create(popinterface::class.java)

        service.getPopularpeople(api_key).enqueue(object : Callback<peopleresponse> {
            override fun onFailure(call: Call<peopleresponse>, t: Throwable) {
                Log.d("MoviesDagger", t.toString())
            }



            override fun onResponse(call: Call<peopleresponse>, response: Response<peopleresponse>) {

                val data=response.body()
                val data1= data?.results
                progressBar3.isVisible=false
                textpeople.isVisible=true


                //  rView.layoutManager =
                //     GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)

                rViewperson.layoutManager =
                    GridLayoutManager(this@MainActivityPeople,2, RecyclerView.VERTICAL,false)
                rViewperson.adapter = data1?.let {
                    popularpeopleadapter(
                        this@MainActivityPeople,
                        it,
                        false
                    )
                }



            }
        })


    }
}
