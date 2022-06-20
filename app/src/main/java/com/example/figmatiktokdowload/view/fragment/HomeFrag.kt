package com.example.figmatiktokdowload.view.fragment

import android.annotation.SuppressLint
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.figmatiktokdowload.R
import com.example.figmatiktokdowload.databinding.FragmentHomeBinding
import com.example.figmatiktokdowload.view.viewmodel.HomeViewModel
import com.squareup.picasso.Picasso


class HomeFrag : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private lateinit var homeViewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false

        )
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding.lifecycleOwner = this
        binding.data = homeViewModel
        return binding.root
    }

    @SuppressLint("ServiceCast")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val clipboardManager =
            context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        binding.btnPatse.setOnClickListener {
            binding.edtlink.setText(clipboardManager.primaryClip?.getItemAt(0)?.text)
            if (!TextUtils.isEmpty(binding.edtlink.text)) {
                binding.btndownload.setBackgroundResource(R.drawable.bgrdown)
            } else {
                binding.btndownload.setBackgroundResource(R.drawable.bgrdown_error)
            }
        }


        val uriObserver =
            Observer<String> { newUri -> Picasso.get().load(newUri).into(binding.imageVideo) }
        homeViewModel.uri.observe(viewLifecycleOwner, uriObserver)


    }


    override fun onDestroy() {
        super.onDestroy()

    }

}


