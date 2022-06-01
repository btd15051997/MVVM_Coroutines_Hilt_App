package com.example.movieapp.api

import com.example.movieapp.Constants
import com.example.movieapp.model.TvShowResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(Constants.END_POINT)
    suspend fun getTvShows(): Response<TvShowResponse>
}