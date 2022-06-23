package com.streamability.datalayer.di

import android.content.Context
import androidx.room.Room
import com.alecbrando.lib_data_layer.data.local.dataPref.DataPrefImpl
import com.alecbrando.lib_data_layer.data.local.dataPref.dataprefint.DataPreference
import com.streamability.datalayer.data.local.room.RoomDao
import com.streamability.datalayer.data.local.room.RoomDb
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
import dagger.hilt.android.qualifiers.ApplicationContext
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
            .baseUrl("https://api.themoviedb.org/3")
            .build()
            .create(MovieDbApiEndpoint::class.java)
    }

    @Provides
    @Singleton
    fun remoteDataSource(ApiEndpoint: MovieDbApiEndpoint): RemoteDataSource = RemoteDataSourceImpl(ApiEndpoint)

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataPreference = DataPrefImpl(context)

    @Provides
    @Singleton
    fun provideDao(db: RoomDb): RoomDao = db.RoomDao()

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): RoomDb =
        Room.databaseBuilder(context, RoomDb::class.java,"TASK_DB")
            .fallbackToDestructiveMigration().build()

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