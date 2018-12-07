package com.dicoding.submission2.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.submission2.R
import kotlinx.android.synthetic.main.fragment_detail_detail.view.*

class DetailDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_detail_detail, container, false)
        v.tv_detail_team.text = arguments?.getString("detail")
        return v
    }
}