package com.dicoding.submission2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.bumptech.glide.Glide
import com.dicoding.submission2.Model.DetailModel
import com.dicoding.submission2.Model.TeamModel
import com.dicoding.submission2.Presenter.DetailPresenter
import com.dicoding.submission2.View.ViewDetail
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), ViewDetail {
    override fun setData(det: DetailModel, homeTeam: TeamModel, awayTeam: TeamModel) {
        progressBar.visibility = View.GONE
        tvDate.text = det.dateEvent
        Glide.with(this).load(homeTeam.emblem).into(ivHome)
        Glide.with(this).load(awayTeam.emblem).into(ivAway)
        tvHome.text = homeTeam.name
        tvAway.text = awayTeam.name
        if (det.intHomeScore == "null") {
            tvHomeScore.text = "-"
        } else {
            tvHomeScore.text = det.intHomeScore

        }
        if (det.intAwayScore == "null") {
            tvAwayScore.text = "-"
        } else {
            tvAwayScore.text = det.intHomeScore

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val presenter = DetailPresenter(this, intent.getStringExtra("idEvent"), this)
        progressBar.visibility = View.VISIBLE
        presenter.getData()
    }

}
