package com.streamability.datalayer.data.remote

import com.streamability.datalayer.domain.models.movieDetails.MovieDetailsModel
import com.streamability.datalayer.domain.models.movieWatchProviders.MovieWatchProvidersModel
import com.streamability.datalayer.domain.models.searchMovie.SearchMovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/***
 Interface for movieDb api. Lists given endpoints
 */
interface MovieDbApiEndpoint {
    @GET("search/movie")
    suspend fun getMovieSearch(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): Response<SearchMovieModel>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String
    ): Response<MovieDetailsModel>

    @GET("movie/{movie_id}/watch/providers")
    suspend fun getMovieWatchProviders(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String
    ): Response<MovieWatchProvidersModel>
}
