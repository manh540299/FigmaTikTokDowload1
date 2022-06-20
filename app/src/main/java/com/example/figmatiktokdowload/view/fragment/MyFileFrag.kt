package com.example.figmatiktokdowload.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.figmatiktokdowload.R
import com.example.figmatiktokdowload.view.adapter.VidByDateAdapter
import com.example.figmatiktokdowload.view.model.database.entity.Video
import com.example.figmatiktokdowload.view.model.model.ByDate
import com.example.figmatiktokdowload.view.model.model.MVideo
import com.example.figmatiktokdowload.view.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_my_file.*

class MyFileFrag : Fragment() {
    private lateinit var vidByDateAdapter: VidByDateAdapter
    private lateinit var homeViewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View=LayoutInflater.from(inflater.context).inflate( R.layout.fragment_my_file,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel=ViewModelProvider(this)[HomeViewModel::class.java]
        val layoutManager=LinearLayoutManager(activity,RecyclerView.VERTICAL,false)
        history.layoutManager=layoutManager
        history.adapter=vidByDateAdapter
        homeViewModel.getAllVideosObServer().observe(viewLifecycleOwner,Observer<List<Video>>{
         val videos=it
            var i:Int=0
            var clmm:MutableList<MVideo> = mutableListOf()
            while (i<videos.size){
                var cl=videos.get(i)
                    clmm.add(MVideo(cl.urlVideo, cl.urlImage, cl.titleVid, cl.nikName, cl.duratiom))
                i++
            }
           var byDate:ByDate= ByDate("10/10/10",clmm)
//            var byDate01:ByDate=ByDate("10/10/10",clmm)
//            var byDate02:ByDate=ByDate("10/10/10",clmm)
//            var byDate03:ByDate=ByDate("10/10/10",clmm)
//            var datemm:MutableList<ByDate> = mutableListOf()
//            datemm.add(byDate)
//            datemm.add(byDate01)
//            datemm.add(byDate02)
//            datemm.add(byDate03)

        })

    }
}