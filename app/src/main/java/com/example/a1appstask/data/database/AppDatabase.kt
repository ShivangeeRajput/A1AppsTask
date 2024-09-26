package com.example.a1appstask.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.a1appstask.models.Manga

@Database(
    entities = [Manga::class, MangaRemoteKeys::class, Favourite::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun mangaDao(): MangaDao
    abstract fun mangaRemoteKeysDao(): RemoteKeyDao
    abstract fun favouriteDao(): FavouriteDao


}