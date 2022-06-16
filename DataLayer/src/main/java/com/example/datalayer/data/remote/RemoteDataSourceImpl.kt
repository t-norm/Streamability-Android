package com.example.datalayer.data.remote

import com.example.datalayer.domain.dataInterfaces.RemoteDataSource
import com.example.datalayer.domain.models.movieDetails.MovieDetailsModel
import com.example.datalayer.domain.models.movieWatchProviders.MovieWatchProvidersModel
import com.example.datalayer.domain.models.searchMovie.SearchMovieModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val movieDbApi: MovieDbApiEndpoint): RemoteDataSource {
    override suspend fun searchMovie(apiKey: String, query: String): Response<SearchMovieModel> = withContext(Dispatchers.IO) {
        return@withContext(movieDbApi.getMovieSearch(apiKey, query))
    }

    override suspend fun movieDetails(movieId: Int, apiKey: String): Response<MovieDetailsModel> = withContext(Dispatchers.IO) {
        return@withContext(movieDbApi.getMovieDetails(movieId, apiKey))
    }

    override suspend fun watchProviders(movieId: Int, apiKey: String): Response<MovieWatchProvidersModel> = withContext(Dispatchers.IO) {
        return@withContext(movieDbApi.getMovieWatchProviders(movieId, apiKey))
    }
}