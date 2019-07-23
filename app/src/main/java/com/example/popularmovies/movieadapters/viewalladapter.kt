package com.example.popularmovies.movieadapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmovies.R
import com.example.popularmovies.Model.movie
import com.example.popularmovies.MovieActivites.activity_second
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_1.view.*

class viewalladapter(val context: Context, val namelist:List<movie>, val check:Boolean): RecyclerView.Adapter<viewalladapter.myviewholder>() {

    val baseURL = "https://image.tmdb.org/t/p/w342/"
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
        val itemView=li.inflate(R.layout.viewalllayout,parent,false)
        return myviewholder(itemView)

    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {

        val item1= this.namelist[position]
        holder.itemView.ltView.text=item1.original_title
        val target=item1.poster_path
        Picasso.get().load(baseURL+target).resize(180,210).into(holder.itemView.liView)

        holder.itemView.parentLayout.setOnClickListener {

            val intent= Intent(context,activity_second::class.java)
            intent.putExtra("id",item1.id)
            intent.putExtra("type","Movie")
            ContextCompat.startActivity(context, intent, null)
        }

    }


}