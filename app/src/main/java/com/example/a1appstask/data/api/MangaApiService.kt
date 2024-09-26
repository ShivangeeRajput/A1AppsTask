package com.example.a1appstask.data.api

import com.example.a1appstask.models.Manga
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface MangaApiService {
    @Headers(
        "X-RapidAPI-Host: mangaverse-api.p.rapidapi.com",
        "X-RapidAPI-Key: 046ed2ddb1msh402330e1e6b9202p163726jsn1b81714babb3"
    )
    @GET("/manga")
    suspend fun getMangaList(): Response<List<Manga>>
}
