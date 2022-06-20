package com.example.figmatiktokdowload.view.model.database.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "videoinfo")
data class Video(
    @PrimaryKey(autoGenerate = true) val idVid: Long=0,
    @ColumnInfo(name = "urlVid")
    @NonNull
    val urlVideo: String,
    @ColumnInfo(name = "urlImage")
    @NonNull
    val urlImage: String,
    @ColumnInfo(name = "titleVid")
    val titleVid: String,
    @ColumnInfo(name = "nickName")
    @NonNull
    val nikName: String,
    @ColumnInfo(name = "duration")
    @NonNull
    val duratiom: Long,
    @ColumnInfo(name = "date")
    @NonNull
    val date: String
)
