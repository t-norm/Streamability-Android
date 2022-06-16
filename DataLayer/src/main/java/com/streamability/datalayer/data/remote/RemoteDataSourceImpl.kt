package com.streamability.datalayer.data.remote

import com.streamability.datalayer.domain.dataInterfaces.RemoteDataSource
import com.streamability.datalayer.domain.models.movieDetails.MovieDetailsModel
import com.streamability.datalayer.domain.models.movieWatchProviders.MovieWatchProvidersModel
import com.streamability.datalayer.domain.models.searchMovie.SearchMovieModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
/***
Implementing Remote Data Interface. This is so we can call Movie DB API
 */
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