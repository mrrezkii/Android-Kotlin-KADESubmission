package com.dicoding.submission2.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.submission2.R
import com.dicoding.submission2.adapter.TeamAdapter
import com.dicoding.submission2.model.TeamListModel
import com.dicoding.submission2.presenter.TeamFavoritePresenter
import com.dicoding.submission2.repository.TeamFavoriteRepo
import com.dicoding.submission2.view.ViewTeam
import kotlinx.android.synthetic.main.fragment_favorite_team.view.*

class TeamFavoriteFragment : Fragment(), ViewTeam {

    private var ls: MutableList<TeamListModel> = mutableListOf()
    private lateinit var presenter: TeamFavoritePresenter
    private lateinit var v: View

    override fun showDataRecycler(ls: MutableList<TeamListModel>) {
        this.ls = ls
        v.swipeRefresh.isRefreshing = false
        if (ls.size > 0) {
            v.tvNoData.visibility = View.INVISIBLE
            v.recyclerViewFavorite.adapter = TeamAdapter(this.context!!, this.ls)
            v.recyclerViewFavorite.layoutManager = LinearLayoutManager(this.context)
        } else {
            v.tvNoData.visibility = View.VISIBLE
        }
    }

    override fun onLoading() {
        ls.clear()
        v.recyclerViewFavorite.adapter?.notifyDataSetChanged()
        v.swipeRefresh.isRefreshing = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_favorite_team, container, false)
        presenter = TeamFavoritePresenter(TeamFavoriteRepo(this.context!!, this))
        presenter.getData()
        v.swipeRefresh.setOnRefreshListener {
            ls.clear()
            v.recyclerViewFavorite.adapter?.notifyDataSetChanged()
            v.swipeRefresh.isRefreshing = true
            presenter.getData()
        }
        return v
    }
}