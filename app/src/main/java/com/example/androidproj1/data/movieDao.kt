package com.example.androidproj1.data
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface movieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addmovie(movie: movie)

    @Query("SELECT * FROM movie_table ORDER BY id ASC")

    fun readAllData(): LiveData<List<movie>>

}