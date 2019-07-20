package com.example.popularmovies

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_1.view.*
import kotlinx.android.synthetic.main.layout_5.view.*

class videoadapter(val context: Context, val namelist:List<video>, val check:Boolean): RecyclerView.Adapter<videoadapter.myviewholder>() {

    var baseURL = "https://www.youtube.com/watch?v="
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
        val itemView=li.inflate(R.layout.layout_5,parent,false)
        return myviewholder(itemView)

    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {

        val item1= this.namelist[position]
         val extraid=item1.key
        val a=item1.id
        val img_url="http://img.youtube.com/vi/$extraid/0.jpg"
        Log.d("amitoz",item1.key)


        Picasso.get().load(img_url).centerCrop().resize(250,200).into(holder.itemView.videoimage)
//      var  videoId=extractYoutubeId("http://www.youtube.com/watch?v=t7UxjpUaL3Y")




        holder.itemView.playbutton.setOnClickListener {
            var i = Intent()
            i.action = Intent.ACTION_VIEW
            i.data = Uri.parse(baseURL + extraid)
            startActivity(context, i, null)
        }

    }


}