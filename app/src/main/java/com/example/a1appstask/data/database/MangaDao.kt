package com.example.a1appstask.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MangaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllManga(mangas: List<Manga>)

    @Query("DELETE FROM ${Constants.MANGA_TABLE}")
    suspend fun deleteAllManga()

    @Query("SELECT * FROM ${Constants.MANGA_TABLE}")
    fun getAllManga(): PagingSource<Int, Manga>
}