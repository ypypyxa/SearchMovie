package com.practiCUM.searchmovie.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.practiCUM.searchmovie.data.db.dao.MovieDao
import com.practiCUM.searchmovie.data.db.entity.MovieEntity


@Database(version = 1, entities = [MovieEntity::class])
abstract class AppDatabase : RoomDatabase(){

    abstract fun movieDao(): MovieDao
}