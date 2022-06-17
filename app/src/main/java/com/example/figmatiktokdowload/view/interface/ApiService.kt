package com.example.figmatiktokdowload.view.`interface`

import com.example.figmatiktokdowload.view.model.api.Api
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
  @GET("/aweme/v1/aweme/detail/")
    fun getLinh(@Query("aweme_id") aweme_id:String):Call<Api>


}