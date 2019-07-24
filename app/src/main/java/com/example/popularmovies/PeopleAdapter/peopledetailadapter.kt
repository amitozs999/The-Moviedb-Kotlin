package com.example.popularmovies.PeopleAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmovies.Model.movie
import com.example.popularmovies.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_6.view.*

class peopledetailadapter(val context: Context, val namelist:List<movie>, val check:Boolean): RecyclerView.Adapter<peopledetailadapter.myviewholder>() {

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
        val itemView=li.inflate(R.layout.layout_1,parent,false)
        return myviewholder(itemView)

    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {

        val item1= this.namelist[position]
        holder.itemView.peoplename.text=item1.original_title
        val target=item1.poster_path
        Picasso.get().load(baseURL+target).into(holder.itemView.imagepeople)

//        holder.itemView.parentLayout.setOnClickListener {
//
//            val intent= Intent(context, activity_second::class.java)
//            intent.putExtra("id",item1.id)
//            intent.putExtra("type","Movie")
//            ContextCompat.startActivity(context, intent, null)
//        }

    }


}