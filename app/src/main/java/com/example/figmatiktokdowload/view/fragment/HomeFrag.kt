package com.example.figmatiktokdowload.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.figmatiktokdowload.R


class HomeFrag : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View=LayoutInflater.from(inflater.context).inflate(R.layout.fragment_home,container,false)
        return view
    }

}