package com.example.figmatiktokdowload.view.model.database.dao

import androidx.room.*
import com.example.figmatiktokdowload.view.model.database.entity.Video



@Dao
interface VideoDao {
    @Query("SELECT * FROM videoinfo ORDER BY idVid DESC")
    fun getListVid() : ArrayList<Video>?
    @Insert
    fun insertVid(video: Video?)
    @Delete
    fun deleteVid(video: Video?)
    @Update
    fun updateVid(video: Video?)
}