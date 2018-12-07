package com.dicoding.submission2.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.dicoding.submission2.fragment.DetailDetailFragment
import com.dicoding.submission2.fragment.DetailMemberFragment


class DetailTeamPagerAdapter(fm: FragmentManager, val bundle: Bundle) : FragmentPagerAdapter(fm) {

    override fun getItem(p0: Int): Fragment {
        when (p0) {
            0 -> {
                val fragment = DetailDetailFragment()
                fragment.arguments = bundle
                return fragment
            }
            1 -> {
                val fragment = DetailMemberFragment()
                fragment.arguments = bundle
                return fragment
            }
        }
        val fragment = DetailDetailFragment()
        fragment.arguments = bundle
        return fragment
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(p0: Int): CharSequence? {
        return when (p0) {
            0 -> "Detail"
            else -> "Players"

        }
    }

}