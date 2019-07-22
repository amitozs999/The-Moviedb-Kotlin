package com.example.popularmovies.tvadapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmovies.TvActivities.MainActivitytv2
import com.example.popularmovies.Model.tv
import com.example.popularmovies.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_1.view.*

class tvadapter(val context: Context, val namelist:List<tv>, val check:Boolean): RecyclerView.Adapter<tvadapter.myviewholder>() {

    val baseURL = "https://image.tmdb.org/t/p/w1280/"
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
        holder.itemView.ltView.text=item1.original_name
        val target=item1.backdrop_path
        Picasso.get().load(baseURL+target).resize(330,200).into(holder.itemView.liView)
        holder.itemView.parentLayout.setOnClickListener {

            val intent= Intent(context, MainActivitytv2::class.java)
            intent.putExtra("id",item1.id)
            intent.putExtra("type","Tv")
            ContextCompat.startActivity(context, intent, null)
        }
    }


}