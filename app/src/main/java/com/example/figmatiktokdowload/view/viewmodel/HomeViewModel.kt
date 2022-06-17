package com.example.figmatiktokdowload.view.viewmodel

import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.figmatiktokdowload.view.`interface`.ApiService
import com.example.figmatiktokdowload.view.model.api.Api
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeViewModel() : ViewModel() {

    private val _link = MutableLiveData<String>()
    val link: MutableLiveData<String> = _link
    private var _check2: MutableLiveData<Boolean> = MutableLiveData(false)
    var check2: LiveData<Boolean> = _check2
    private var _check: MutableLiveData<Boolean> = MutableLiveData(false)
    var check: LiveData<Boolean> = _check
    private var _uri = MutableLiveData<String>()
    var uri: LiveData<String> = _uri


    fun onDownLoad() {
        val linh: String? = _link.value

        if (linh != null) {
            val idVid = linh.substringAfterLast("/").substringBefore("?")
            if (!TextUtils.isEmpty(idVid))
                checkDownLoad(idVid)
            else _check.value = true
        } else {
            _check.value = true
        }
    }


    fun checkDownLoad(idVid: String) {
        val retrofitBuild = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api2.musical.ly").build().create(ApiService::class.java)
        val dataz = retrofitBuild.getLinh(idVid)
        dataz.enqueue(object : Callback<Api?> {
            override fun onResponse(call: Call<Api?>?, response: Response<Api?>?) {
                _check2.value = true
                val responsea = response?.body()
                val linkk = responsea?.aweme_detail?.video?.play_addr?.url_list?.get(2)
                _uri.value = responsea?.aweme_detail?.video?.origin_cover?.url_list?.get(0)
                if (linkk != null) {
                    link.value = linkk!!
                }
            }

            override fun onFailure(call: Call<Api?>?, t: Throwable?) {
                _check.value = true
            }
        })


    }

}







