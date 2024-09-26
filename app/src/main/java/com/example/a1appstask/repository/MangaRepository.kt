package com.example.a1appstask.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.a1appstask.data.api.MangaApiService
import com.example.a1appstask.data.database.AppDatabase
import com.example.a1appstask.data.database.FavouriteDao
import com.example.a1appstask.models.Manga
import com.prplmnstr.a1appstask.model.Favorite


import javax.inject.Inject

class MangaRepository @Inject constructor(
    private val api: MangaApiService,
    private val appDatabase: AppDatabase,
    private val favoriteDao: FavouriteDao

) {

    @OptIn(ExperimentalPagingApi::class)
    fun getManga() = Pager(
        config = PagingConfig(pageSize = 25, maxSize = 100),
        remoteMediator = MangaRemoteMediator(api, appDatabase),
        pagingSourceFactory = { appDatabase.mangaDao().getAllManga() }
    )
        .liveData

    //favorite operation

    val favorites = favoriteDao.getAllFavorites()

    suspend fun insertToFavorite(favorite: Favorite) = favoriteDao.insertToFavorite(favorite)

    suspend fun deleteFavorite(favorite: Favorite) = favoriteDao.deleteFavourite(favorite)

    suspend fun deleteFavorites(favorites: List<Favorite>) = favoriteDao.deleteFavourites(favorites)
}