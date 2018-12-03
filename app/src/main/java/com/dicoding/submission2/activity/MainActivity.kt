package com.dicoding.submission2.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dicoding.submission2.R
import com.dicoding.submission2.adapter.MyPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewpager_main.adapter = MyPagerAdapter(supportFragmentManager)
        tabs_main.setupWithViewPager(viewpager_main)
    }
}
