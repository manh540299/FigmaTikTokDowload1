package com.example.figmatiktokdowload.view.model.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.figmatiktokdowload.view.model.database.dao.VideoDao
import com.example.figmatiktokdowload.view.model.database.entity.Video

@Database(entities = [Video::class], version = 1, exportSchema = false)
abstract class VideoDatabase : RoomDatabase() {
    abstract fun videoDao(): VideoDao?

    companion object {
        @Volatile
        private var INSTANCE: VideoDatabase? = null
        fun getDatabase(context: Context): VideoDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder<VideoDatabase>(
                    context.applicationContext,
                    VideoDatabase::class.java,
                    "DownTikTokDB"
                ).allowMainThreadQueries().build()
            }
            return INSTANCE
        }
    }
}