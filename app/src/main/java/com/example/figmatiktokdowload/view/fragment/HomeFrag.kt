package com.example.figmatiktokdowload.view.fragment

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.figmatiktokdowload.R
import com.example.figmatiktokdowload.view.model.model.MVideo
import com.example.figmatiktokdowload.view.viewmodel.HomeViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFrag : Fragment() {
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View =
            LayoutInflater.from(inflater.context).inflate(R.layout.fragment_home, container, false)

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickPatse()
        clickDownLoad()



    }


    private fun clickPatse() {
        val clipboardManager =
            context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        btn_patse.setOnClickListener {
            edtlink.setText(clipboardManager.primaryClip?.getItemAt(0)?.text)
            if (!TextUtils.isEmpty(edtlink.text)) {
                btndownload.setBackgroundResource(R.drawable.bgrdown)
            } else {
                btndownload.setBackgroundResource(R.drawable.bgrdown_error)
            }
        }
    }

    private fun clickDownLoad() {
        btndownload.setOnClickListener {
            homeViewModel.onDownLoad(edtlink.text.toString())
            if (homeViewModel.check.value == true) {
                tvlinkfail.visibility = View.VISIBLE
            } else {
                downvid.visibility = View.VISIBLE
                textView3.visibility = View.VISIBLE
                textView4.visibility = View.VISIBLE
                img_pic.visibility = View.VISIBLE
                homeViewModel._video.observe(viewLifecycleOwner, Observer<MVideo> { newVid ->
                    Picasso.get().load(newVid.urlImage).into(image_video)
                    tvtitle.setText(newVid.title)
                    textView.setText("@"+newVid.nikname)
                    tvduration.setText(String.format("%02d:%02d",(newVid.duration%3600)/60,newVid.duration%60))
                })
            }
        }
    }
}


