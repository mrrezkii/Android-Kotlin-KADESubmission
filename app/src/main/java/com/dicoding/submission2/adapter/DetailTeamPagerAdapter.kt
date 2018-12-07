package com.dicoding.submission2.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.dicoding.submission2.fragment.DetailMemberFragment
import com.dicoding.submission2.fragment.DetailPlayerFragment


class DetailTeamPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    // sebuah list yang menampung objek Fragment
    private val pages: MutableList<Fragment> = mutableListOf()

    init {
        pages.add(DetailMemberFragment())
        pages.add(DetailPlayerFragment())
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
            0 -> "Member"
            else -> "Player"

        }
    }
}