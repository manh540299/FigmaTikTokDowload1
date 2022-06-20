package com.example.figmatiktokdowload.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.figmatiktokdowload.R
import com.example.figmatiktokdowload.view.model.model.ByDate
import com.example.figmatiktokdowload.view.model.model.MVideo
import com.example.figmatiktokdowload.view.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.lv_history.view.*

class VidByDateAdapter() : RecyclerView.Adapter<VidByDateAdapter.ViewHodel>() {
    var byDate:List<ByDate> = listOf()
    lateinit var context: HomeViewModel
    fun setData(data: List<ByDate>) {
        this.byDate = data
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VidByDateAdapter.ViewHodel {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.lv_history, parent, false)
        return ViewHodel(inflater)
    }

    override fun onBindViewHolder(holder: VidByDateAdapter.ViewHodel, position: Int) {
        holder.bind(byDate[position])


    }

    override fun getItemCount(): Int {
        return byDate.size
    }

    class ViewHodel(view: View) : RecyclerView.ViewHolder(view) {
        val tvDate = view.tvdate
        val rcvVid = view.list_vid
        fun bind(data: ByDate) {
            val manager=LinearLayoutManager(itemView.context,RecyclerView.VERTICAL,false)
            val vidAdapter = VidAdapter()
            vidAdapter.setData(data.listVid)
            rcvVid.adapter = vidAdapter
            rcvVid.layoutManager = manager
            tvDate.text = data.date
        }

    }
}