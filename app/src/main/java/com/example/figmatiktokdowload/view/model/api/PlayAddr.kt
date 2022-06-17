package com.example.figmatiktokdowload.view.model.api

data class PlayAddr(
    val data_size: Int,
    val file_cs: String,
    val file_hash: String,
    val height: Int,
    val uri: String,
    val url_key: String,
    val url_list: List<String>,
    val width: Int
)