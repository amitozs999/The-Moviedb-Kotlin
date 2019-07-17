package com.example.popularmovies

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_1.view.*

class upmovieadapter(val context: Context, val namelist:List<movie>, val check:Boolean): RecyclerView.Adapter<upmovieadapter.myviewholder>() {

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
        val itemView=li.inflate(R.layout.layout_2,parent,false)
        return myviewholder(itemView)

    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {

        val item1= this.namelist[position]
        holder.itemView.ltView.text=item1.original_title
        val target=item1.backdrop_path
        Picasso.get().load(baseURL+target).into(holder.itemView.liView)
    }


}