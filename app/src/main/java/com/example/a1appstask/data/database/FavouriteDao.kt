package com.example.a1appstask.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavouriteDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToFavorite(favourite: Favourite)

    @Delete
    suspend fun deleteFavourite(favourite: Favourite)

    @Delete
    suspend fun deleteFavourites(favourites: List<Favourite>)


    @Query("SELECT * FROM ${Constants.FAVORITE_TABLE}")
    fun getAllFavorites(): LiveData<List<Favorite>>
}