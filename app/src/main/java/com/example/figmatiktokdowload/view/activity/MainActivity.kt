package com.example.figmatiktokdowload.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.figmatiktokdowload.R
import com.example.figmatiktokdowload.view.fragment.ExploreFrag
import com.example.figmatiktokdowload.view.fragment.HomeFrag
import com.example.figmatiktokdowload.view.fragment.MyFileFrag
import com.example.figmatiktokdowload.view.fragment.TrendingFrag
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var bottom_nav: BottomNavigationView
    var currentFrag:Int=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFrag(HomeFrag())
        setBotNav()
    }

    private fun loadFrag(fragment: Fragment) {
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun setBotNav() {
        bottom_nav = findViewById(R.id.botnav)
        bottom_nav.setOnItemSelectedListener {
            when (it.itemId) {
              R.id.home -> {
                  openHomeFragment()
              }
                R.id.myfile ->{
                  openMyFileFragment()
                }
                R.id.trending->{
                 openTrendingFragment()
                }
                R.id.explore->{
                    openExploreFragment()
                }
          }
        true
        }
    }
    private fun openHomeFragment() {
        var homeFragment : Int  = 1
        if (currentFrag != homeFragment) {
            loadFrag(HomeFrag());
            currentFrag= homeFragment
        }
    }
    private fun openMyFileFragment() {
        var myFileFragment : Int  = 2
        if (currentFrag != myFileFragment) {
            loadFrag(MyFileFrag());
            currentFrag= myFileFragment
        }
    }
    private fun openTrendingFragment() {
        var trendingFragment : Int  = 3
        if (currentFrag != trendingFragment) {
            loadFrag(TrendingFrag());
            currentFrag= trendingFragment
        }
    }
    private fun openExploreFragment() {
        var exploreFragment : Int  = 3
        if (currentFrag != exploreFragment) {
            loadFrag(ExploreFrag());
            currentFrag= exploreFragment
        }
    }
}