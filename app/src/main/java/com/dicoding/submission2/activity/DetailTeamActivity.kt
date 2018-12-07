package com.dicoding.submission2.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.submission2.DBHelper
import com.dicoding.submission2.R
import com.dicoding.submission2.adapter.DetailTeamPagerAdapter
import com.dicoding.submission2.model.TeamListModel
import com.dicoding.submission2.presenter.TeamDetailPresenter
import com.dicoding.submission2.repository.TeamDetailRepo
import com.dicoding.submission2.view.ViewTeamDetail
import kotlinx.android.synthetic.main.activity_detail_team.*

class DetailTeamActivity : AppCompatActivity(), ViewTeamDetail {
    lateinit var bundle: Bundle
    private var favorite = false
    private val database = DBHelper(this)
    override fun onDataLoaded(detail: TeamListModel) {
        bundle = Bundle()
        bundle.putString("id", detail.id)
        bundle.putString("detail", detail.desc)
        if (!this.isFinishing) {
            Glide.with(this).load(detail.emblem).into(iv_header)
        }
        collapsing_toolbar.title = detail.name

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        val adapter = DetailTeamPagerAdapter(supportFragmentManager)
        viewpager_main.adapter = adapter
        tabs_main.setupWithViewPager(viewpager_main)
        val teamDetailPresenter = TeamDetailPresenter(intent.getStringExtra("id"), TeamDetailRepo(this, this))
        teamDetailPresenter.getData()
        nested.isFillViewport = true
        /*database.use {
            val result = select(TeamFavoriteModel.TABLE_TEAM_FAVORITE).whereArgs("(" + TeamFavoriteModel.TEAM_ID + ") = {id}", "id" to intent.getStringExtra("id")).exec { parseList(
                classParser<TeamFavoriteModel>()
            ) }
            if (!result.isEmpty()) favorite = true
        }*/
    }
}
