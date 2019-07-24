package com.example.popularmovies

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favoritetable")
data class Favourite (

    @PrimaryKey(autoGenerate = true) val id : Long?=null,

     @ColumnInfo(name = "movieidcol") var movie_id : String,
    @ColumnInfo(name = "path") var path:String
//    @ColumnInfo(name = "titlecol") var title:String,
//    @ColumnInfo(name = "imagepathcol") var imagepath:String




)