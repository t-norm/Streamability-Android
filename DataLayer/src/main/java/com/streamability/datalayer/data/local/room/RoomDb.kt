package com.streamability.datalayer.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.streamability.datalayer.domain.models.Room.MovieSearch

@Database(entities = [MovieSearch::class], exportSchema = false, version = 1)
abstract class RoomDb: RoomDatabase() {
    abstract fun RoomDao() : RoomDao
}