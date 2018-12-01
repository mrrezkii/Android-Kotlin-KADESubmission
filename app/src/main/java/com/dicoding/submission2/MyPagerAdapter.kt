package com.dicoding.submission2

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.dicoding.submission2.Fragment.FavoriteFragment
import com.dicoding.submission2.Fragment.LastMatchFragment
import com.dicoding.submission2.Fragment.NextMatchFragment

class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    // sebuah list yang menampung objek Fragment
    private val pages: MutableList<Fragment> = mutableListOf()

    init {
        pages.add(LastMatchFragment())
        pages.add(NextMatchFragment())
        pages.add(FavoriteFragment())
    }
    // menentukan fragment yang akan dibuka pada posisi tertentu
    override fun getItem(position: Int): Fragment? {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    // judul untuk tabs
    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Last Match"
            1 -> "Next Match"
            else -> "FavoriteModel"
        }
    }
}