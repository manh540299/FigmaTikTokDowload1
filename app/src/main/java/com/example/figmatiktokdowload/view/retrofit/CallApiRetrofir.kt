package com.example.figmatiktokdowload.view.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CallApiRetrofir {
    val retrofitBuild = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api2.musical.ly").build().create(ApiService::class.java)
}