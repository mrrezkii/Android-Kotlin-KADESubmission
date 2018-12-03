package com.dicoding.submission2.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.submission2.R
import com.dicoding.submission2.adapter.RecyclerViewAdapter
import com.dicoding.submission2.model.MatchModel
import com.dicoding.submission2.presenter.FavoritePresenter
import com.dicoding.submission2.repository.FavoriteRepo
import com.dicoding.submission2.view.ViewAdapter
import kotlinx.android.synthetic.main.fragment_favorite.view.*

class FavoriteFragment : Fragment(), ViewAdapter {
    private lateinit var presenter: FavoritePresenter
    private lateinit var v: View
    private var list: MutableList<MatchModel> = mutableListOf()
    override fun showDataRecycler(ls: MutableList<MatchModel>) {
        this.list = ls
        v.swipeRefresh.isRefreshing = false
        if (list.size > 0) {
            v.tvNoData.visibility = View.INVISIBLE
            v.recyclerViewFavorite.adapter =
                    RecyclerViewAdapter(this.context!!, this.list)
            v.recyclerViewFavorite.layoutManager = LinearLayoutManager(this.context)
        } else {
            v.tvNoData.visibility = View.VISIBLE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_favorite, container, false)
        presenter = FavoritePresenter(FavoriteRepo(this.context!!, this))
        presenter.getData()
        v.swipeRefresh.setOnRefreshListener {
            list.clear()
            v.recyclerViewFavorite.adapter?.notifyDataSetChanged()
            v.swipeRefresh.isRefreshing = true
            presenter.getData()
        }




        return v
    }


}
