package com.movietvapp.popularmovies

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface  FavouriteDao{

    @Query("select * from favoritetable" )
    fun getallfav():List<Favourite>

    @Query("delete from favoritetable where movieidcol = :id")
    fun delete(id:String)

    @Insert
    fun insertRow(favourite: Favourite)

    @Query("Select * from favoritetable where movieidcol =  :id")
    fun isFavourite(id : String) : Favourite


}