package com.streamability.datalayer.data.local.room

import androidx.room.*
import com.streamability.datalayer.domain.models.Room.MovieSearch

@Dao
interface RoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewSearchResult (search: MovieSearch)

    @Query("SELECT * FROM SearchTable WHERE id = :id")
    suspend fun getSingleTaskByID (id: Int) : MovieSearch

    @Query("SELECT * FROM SearchTable")
    suspend fun getLocalTasks() : List<MovieSearch>

    @Delete
    suspend fun deleteTask(searchItem: MovieSearch)
}