package com.streamability.datalayer.di

import com.streamability.datalayer.data.remote.MovieDbApiEndpoint
import com.streamability.datalayer.data.remote.RemoteDataSourceImpl
import com.streamability.datalayer.data.repo.Repository
import com.streamability.datalayer.domain.dataInterfaces.RemoteDataSource
import com.streamability.datalayer.domain.useCases.UseCases
import com.streamability.datalayer.domain.useCases.movieUseCases.MovieDetailsUseCase
import com.streamability.datalayer.domain.useCases.movieUseCases.SearchMovieUseCase
import com.streamability.datalayer.domain.useCases.movieUseCases.WatchProvidersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/***
The start of dependency Injection within the data layer. Here we define our data sources,
Room, and our Builder for retrofit
 */

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun movieDbApiService(): MovieDbApiEndpoint {
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.themoviedb.org/3/")
            .build()
            .create(MovieDbApiEndpoint::class.java)
    }

    @Provides
    @Singleton
    fun remoteDataSource(ApiEndpoint: MovieDbApiEndpoint): RemoteDataSource = RemoteDataSourceImpl(ApiEndpoint)

    @Provides
    @Singleton
    fun useCases(repo: Repository): UseCases {
        return UseCases(
            movieDetailsUseCase = MovieDetailsUseCase(repo),
            searchMovieUseCase = SearchMovieUseCase(repo),
            watchProvidersUseCase = WatchProvidersUseCase(repo)
        )
    }
}