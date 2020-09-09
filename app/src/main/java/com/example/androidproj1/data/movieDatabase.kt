package com.example.androidproj1.data
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [movie::class], version = 1, exportSchema = false)
abstract class movieDatabase: RoomDatabase() {

    abstract fun movieDao(): movieDao

    companion object {
        @Volatile
        private var INSTANCE: movieDatabase? =null

        fun getDatabase(context: Context): movieDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized( this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    movieDatabase::class.java,
                    "movie_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}