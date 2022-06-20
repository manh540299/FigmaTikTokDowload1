package com.example.figmatiktokdowload.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.figmatiktokdowload.R
import com.example.figmatiktokdowload.view.model.model.MVideo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.lv_video.view.*

class VidAdapter : RecyclerView.Adapter<VidAdapter.ViewHolder>() {
    var videos: List<MVideo> = listOf()
    fun setData(data: List<MVideo>) {
        this.videos = data
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgVid=view.img_vid
        val tvTitle=view.tvtitle
        val tvig=view.tvig
        val tvfileName=view.tv_file_name
        fun bind(data: MVideo){
            Picasso.get().load(data.urlImage).into(imgVid)
            tvTitle.setText(data.title)
            tvig.setText(data.nikname)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.lv_video, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      holder.bind(videos[position])
    }

    override fun getItemCount(): Int {
        return videos.size
    }
}