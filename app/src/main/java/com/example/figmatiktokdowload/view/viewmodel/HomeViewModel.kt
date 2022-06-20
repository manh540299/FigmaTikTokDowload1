package com.example.figmatiktokdowload.view.viewmodel

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.figmatiktokdowload.view.`interface`.ApiService
import com.example.figmatiktokdowload.view.model.api.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeViewModel : ViewModel() {
    private val _link = MutableLiveData<String>()
    val link: MutableLiveData<String> = _link
    private var _check2: MutableLiveData<Boolean> = MutableLiveData(false)
    var check2: LiveData<Boolean> = _check2
    private var _check: MutableLiveData<Boolean> = MutableLiveData(false)
    var check: LiveData<Boolean> = _check
    private var _uri = MutableLiveData<String>()
    private var _title=MutableLiveData<String>()
    var title=_title
    private var _nikname=MutableLiveData<String>()
    var nickname=_nikname
    val uri: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    fun onDownLoad() {
        val linh: String? = _link.value

        if (linh != null) {
            val idVid = linh.substringAfterLast("/").substringBefore("?")
            if (!TextUtils.isEmpty(idVid))
                checkDownLoad(idVid)
        }
    }


    fun checkDownLoad(idVid: String) {
        val retrofitBuild = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api2.musical.ly").build().create(ApiService::class.java)
        val dataz = retrofitBuild.getLinh(idVid)
        dataz.enqueue(object : Callback<Api?> {
            override fun onResponse(call: Call<Api?>?, response: Response<Api?>?) {
                val _responsea = response?.body()
                if (_responsea?.aweme_detail==null) {
                    _check.value = true
                } else {
                    _check2.value = true
                    val linkk = _responsea?.aweme_detail?.video?.play_addr?.url_list?.get(2)
                    val az = _responsea?.aweme_detail?.video?.origin_cover?.url_list?.get(1)
                    val titlez = _responsea?.aweme_detail?.desc
                    val niknamez = _responsea?.aweme_detail?.author?.unique_id
                    _title.value = titlez
                    _nikname.value ="@"+niknamez
                    if (linkk != null) {
                        _link.value = linkk
                        uri.value = az
                    }

                }
            }

            override fun onFailure(call: Call<Api?>?, t: Throwable?) {
                _check.value = true
            }
        })


    }
    fun onDownLoadVideo(){

    }

}







