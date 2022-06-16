package com.example.datalayer.domain.dataInterfaces

import com.example.datalayer.domain.models.movieDetails.MovieDetailsModel
import com.example.datalayer.domain.models.movieWatchProviders.MovieWatchProvidersModel
import com.example.datalayer.domain.models.searchMovie.SearchMovieModel
import retrofit2.Response

interface RemoteDataSource {
    suspend fun searchMovie(apiKey: String, query: String): Response<SearchMovieModel>

    suspend fun movieDetails(movieId: Int, apiKey: String): Response<MovieDetailsModel>

    suspend fun watchProviders(movieId: Int, apiKey: String): Response<MovieWatchProvidersModel>
}