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
import android.widget.AbsListView
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
import com.movietvapp.popularmovies.Model.people
import kotlinx.android.synthetic.main.activity_5.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_viewallact.*
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
    var isScrolling : Boolean = false
    var currentItems : Int = 0
    var totalItems : Int = 0
    var scrolledOutItems : Int = 0
    var currentPage : Int = 1
    var i = 0
    var count = 0
    lateinit var peopleList : ArrayList<people>
    lateinit var layoutManager: RecyclerView.LayoutManager
    private var gridLayoutManager: GridLayoutManager? = null

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


        fun toBeCalled()

        {
            val service=retrofit.create(popinterface::class.java)

            service.getPopularpeople(api_key,currentPage.toString()).enqueue(object : Callback<peopleresponse> {
                override fun onFailure(call: Call<peopleresponse>, t: Throwable) {
                    Log.d("MoviesDagger", t.toString())
                }



                override fun onResponse(call: Call<peopleresponse>, response: Response<peopleresponse>) {

                    val data=response.body()
                    val data1= data!!.results
                    progressBar3.isVisible=false
                    textpeople.isVisible=true


                    //  rView.layoutManager =
                    //     GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)


                    if(i==0) {
                        peopleList = data1
                        rViewperson.layoutManager =
                            GridLayoutManager(this@MainActivityPeople, 2)
                        rViewperson.adapter = popularpeopleadapter(
                            this@MainActivityPeople,
                            peopleList,
                            false
                        )
                    }
                    else {
                        peopleList.addAll(data1)
                        rViewperson.adapter!!.notifyDataSetChanged()

                    }
                    i++




                }
            })

        }

        toBeCalled()

        rViewperson.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                layoutManager = rViewperson.layoutManager!!
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


}
