package com.movietvapp.popularmovies.tvadapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movietvapp.popularmovies.Model.*
import com.movietvapp.popularmovies.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_3.view.*

class tvcastdapter(val context: Context, val namelist:List<tvcast>, val check:Boolean): RecyclerView.Adapter<tvcastdapter.myviewholder>() {

    val baseURL = "https://image.tmdb.org/t/p/w342/"


    override fun getItemCount(): Int {
        if(check==false)
            if (namelist != null) {
                return namelist.size

            }

        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {

        var li=parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView=li.inflate(R.layout.layout_3,parent,false)
        return myviewholder(itemView)

    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {

        val item1= this.namelist[position]
        if(item1.name!=null)
        {
            holder.itemView.castname.text=item1.name

            val target=item1.profile_path
            Picasso.get().load(baseURL+target).resize(140,120).into(holder.itemView.castimage)}

        else
        {
            holder.itemView.castname.text=" "

        }




//        holder.itemView.parentLayout.setOnClickListener {
//
//            val intent= Intent(context,activity_second::class.java)
//            intent.putExtra("id",item1.id)
//            intent.putExtra("type","Movie")
//            ContextCompat.startActivity(context, intent, null)
//        }

    }

    inner class myviewholder(itemView:View):RecyclerView.ViewHolder(itemView)
}