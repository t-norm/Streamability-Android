package com.streamability.datalayer.domain.dataInterfaces

import com.streamability.datalayer.domain.models.movieDetails.MovieDetailsModel
import com.streamability.datalayer.domain.models.movieWatchProviders.MovieWatchProvidersModel
import com.streamability.datalayer.domain.models.searchMovie.SearchMovieModel
import retrofit2.Response

/***
Interface for making api calls. Structured to how we will be calling them
 */

interface RemoteDataSource {
    suspend fun searchMovie(apiKey: String, query: String): Response<SearchMovieModel>

    suspend fun movieDetails(movieId: Int, apiKey: String): Response<MovieDetailsModel>

    suspend fun watchProviders(movieId: Int, apiKey: String): Response<MovieWatchProvidersModel>
}