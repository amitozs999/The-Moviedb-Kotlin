package com.movietvapp.popularmovies

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities =[Favourite::class],version = 2)
abstract class FavouriteDatabase:RoomDatabase() {

    abstract fun FavDao() : FavouriteDao
    companion object
    {
        var Instance:FavouriteDatabase?=null
        fun getfavouriteDatabase(context:android.content.Context):FavouriteDatabase?{

            if(Instance==null)
            {
                synchronized(FavouriteDatabase::class){
                    Instance= Room.databaseBuilder(context,FavouriteDatabase::class.java,"myDB").allowMainThreadQueries().build()

                }
            }
            return Instance
        }

        fun destroyinstance()
        {
            Instance==null
        }
    }


}