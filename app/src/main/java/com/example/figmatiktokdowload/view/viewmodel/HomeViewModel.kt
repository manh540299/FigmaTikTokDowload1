package com.example.figmatiktokdowload.view.viewmodel


import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.*
import com.example.figmatiktokdowload.view.model.api.Api
import com.example.figmatiktokdowload.view.model.database.database.VideoDatabase
import com.example.figmatiktokdowload.view.model.database.entity.Video
import com.example.figmatiktokdowload.view.model.model.MVideo
import com.example.figmatiktokdowload.view.retrofit.CallApiRetrofir
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HomeViewModel(app: Application) : AndroidViewModel(app) {
    var check: MutableLiveData<Boolean> = MutableLiveData()
    val uri: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    var _video:MutableLiveData<MVideo> = MutableLiveData()

    var allVideos: MutableLiveData<ArrayList<Video>>

    init {
        check.value=false
        _video=MutableLiveData()
        allVideos = MutableLiveData()
    }
    fun getAllVideosObServer(): MutableLiveData<ArrayList<Video>> {
        return allVideos
    }
    fun onDownLoad(link: String) {
        val idVid = link.substringAfterLast("/").substringBefore("?")
        if (!TextUtils.isEmpty(idVid))
            checkDownLoad(idVid)
    }


    fun checkDownLoad(idVid: String) {
        val calApi = CallApiRetrofir().retrofitBuild.getLinh(idVid)
        calApi.enqueue(object : Callback<Api> {
            override fun onResponse(call: Call<Api>?, response: Response<Api>?) {
                val _responsea = response?.body()
                if (_responsea?.aweme_detail == null) {
                    check.value = true
                } else {
                    val link = _responsea.aweme_detail.video.play_addr.url_list.get(2)
                    val linkimg = _responsea.aweme_detail.video.origin_cover.url_list.get(1)
                    val titlez = _responsea.aweme_detail.desc
                    val niknamez = _responsea.aweme_detail.author.unique_id
                    val duration=_responsea.aweme_detail.video.duration/1000
                    val formater = SimpleDateFormat("dd/MM/yyyy")
                    val date = Date()
                    val _date:String=formater.format(date)
                    val vid = Video(0, link, linkimg, titlez, niknamez,duration,_date)
                    val mvid=MVideo(link,linkimg,titlez,niknamez,duration)
                    _video.value=mvid
                    //insertVideo(vid)
                }
            }

            override fun onFailure(call: Call<Api>?, t: Throwable?) {
                check.value = true
            }
        })
    }

    fun onDownLoadVideo() {

    }




    fun getAllVideo() {
        val vidDao = VideoDatabase.getDatabase((getApplication()))?.videoDao()
        val list = vidDao?.getListVid()
        allVideos.postValue(list!!)
    }

    fun insertVideo(vid: Video) {
        val vidDao = VideoDatabase.getDatabase((getApplication()))?.videoDao()
        vidDao?.insertVid(vid)
        getAllVideo()
    }

}







