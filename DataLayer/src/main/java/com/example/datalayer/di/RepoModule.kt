package com.example.datalayer.di

import com.example.datalayer.data.remote.MovieDbApiEndpoint
import com.example.datalayer.data.remote.RemoteDataSourceImpl
import com.example.datalayer.data.repo.Repository
import com.example.datalayer.domain.dataInterfaces.RemoteDataSource
import com.example.datalayer.domain.useCases.UseCases
import com.example.datalayer.domain.useCases.movieUseCases.MovieDetailsUseCase
import com.example.datalayer.domain.useCases.movieUseCases.SearchMovieUseCase
import com.example.datalayer.domain.useCases.movieUseCases.WatchProvidersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun movieDbApiService(): MovieDbApiEndpoint {
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.themoviedb.org/3")
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