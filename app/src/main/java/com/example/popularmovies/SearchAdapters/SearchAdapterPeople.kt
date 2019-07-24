package com.example.popularmovies.SearchAdapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmovies.Model.Search
import com.example.popularmovies.PeopleActivities.MainActivityPeople2
import com.example.popularmovies.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_1.view.*

class SearchAdapterPeople(val context: Context, val namelist:List<Search>, val check:Boolean): RecyclerView.Adapter<SearchAdapterPeople.myviewholder>() {

    val baseURL = "https://image.tmdb.org/t/p/w780/"
    class myviewholder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int {
        if(check==false)
            if (namelist != null) {
                return namelist.size

            }

        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {

        var li=parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView=li.inflate(R.layout.seachlayout,parent,false)
        return myviewholder(itemView)

    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
        val item1= this.namelist[position]



            holder.itemView.ltView.text = item1.name

        var target : String

            target = item1.profile_path

        Picasso.get().load(baseURL + target).into(holder.itemView.liView)




        holder.itemView.parentLayout.setOnClickListener {

            val intent= Intent(context, MainActivityPeople2::class.java)
            intent.putExtra("id",item1.id)
            intent.putExtra("type","people")
            ContextCompat.startActivity(context, intent, null)
        }
    }


}