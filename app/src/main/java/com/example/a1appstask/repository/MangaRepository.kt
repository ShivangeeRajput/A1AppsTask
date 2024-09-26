package com.example.a1appstask.repository

import com.example.a1appstask.data.api.MangaApiService
import com.example.a1appstask.models.Manga



import javax.inject.Inject

class MangaRepository @Inject constructor(private val mangaAPI: MangaApiService) {
    suspend fun getMangaList(): List<Manga>? {
        val response = mangaAPI.getMangaList()
        return if (response.isSuccessful) response.body() else null
    }
}
