package com.dicoding.submission2.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.submission2.R
import com.dicoding.submission2.adapter.MemberAdapter
import com.dicoding.submission2.model.MemberModel
import com.dicoding.submission2.presenter.MemberListPresenter
import com.dicoding.submission2.repository.MemberListRepo
import com.dicoding.submission2.view.ViewMemberList
import kotlinx.android.synthetic.main.fragment_player_detail.view.*

class DetailPlayerFragment : Fragment(), ViewMemberList {
    private lateinit var v: View
    override fun onDataLoaded(mutableList: MutableList<MemberModel>) {
        v.rv_member.adapter = MemberAdapter(this.context!!, mutableList)
        v.rv_member.layoutManager = LinearLayoutManager(this.context!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_player_detail, container, false)
        val p = MemberListPresenter(arguments?.getString("id")!!, MemberListRepo(this, this.context!!))
        p.getData()
        return v
    }
}