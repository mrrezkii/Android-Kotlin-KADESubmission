package com.dicoding.submission2.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.submission2.R
import com.dicoding.submission2.model.MemberModel
import com.dicoding.submission2.presenter.MemberDetailPresenter
import com.dicoding.submission2.repository.MemberDetailRepo
import com.dicoding.submission2.view.ViewMemberDetail
import kotlinx.android.synthetic.main.activity_member_detail.*

class MemberDetailActivity : AppCompatActivity(), ViewMemberDetail {
    override fun onDataLoaded(member: MemberModel) {
        if (!this.isFinishing) {
            Glide.with(this).load(member.thumbnail).into(iv_header)
        }
        collapsing_toolbar.title = member.name
        tv_member_detail.text = member.desc
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_detail)
        nested.isFillViewport = true
        val presenter = MemberDetailPresenter(intent.getStringExtra("id"), MemberDetailRepo(this, this))
        presenter.getData()
    }
}
