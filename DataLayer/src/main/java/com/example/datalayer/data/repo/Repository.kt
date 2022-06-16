package com.example.datalayer.data.repo

import com.example.datalayer.domain.dataInterfaces.RemoteDataSource
import com.example.datalayer.domain.models.movieDetails.MovieDetailsModel
import com.example.datalayer.domain.models.movieWatchProviders.MovieWatchProvidersModel
import com.example.datalayer.domain.models.searchMovie.SearchMovieModel
import com.example.datalayer.utils.Resource
import javax.inject.Inject

class Repository @Inject constructor(private val remote: RemoteDataSource) {

    suspend fun getMovieSearch(apiKey: String, query: String): Resource<SearchMovieModel> {
        val movieSearch = remote.searchMovie(apiKey, query)
    }

    suspend fun getMovieDetails(): Resource<MovieDetailsModel> {

    }

    suspend fun getWatchProviders(): Resource<MovieWatchProvidersModel> {

    }
}