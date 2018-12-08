package com.dicoding.submission2.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.submission2.R
import com.dicoding.submission2.RecyclerViewAdapter
import com.dicoding.submission2.model.MatchModel
import com.dicoding.submission2.model.MatchModelResponse
import com.dicoding.submission2.presenter.MatchPresenter
import com.dicoding.submission2.repository.MatchRepo
import com.dicoding.submission2.view.MatchView
import com.dicoding.submission2.view.ViewAdapter
import kotlinx.android.synthetic.main.fragment_next_match.*
import kotlinx.android.synthetic.main.fragment_next_match.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class NextMatchFragment : Fragment(), MatchView, ViewAdapter {
    override fun onShowLoading() {

    }

    override fun onHideLoading() {

    }

    override fun onDataLoaded(data: MatchModelResponse?) {

    }

    override fun onDataError() {

    }

    private var list: MutableList<MatchModel> = mutableListOf()
    private lateinit var v: View
    private lateinit var adapter: RecyclerViewAdapter
    override fun showDataRecycler(ls: MutableList<MatchModel>) {
        list = ls
        adapter = RecyclerViewAdapter(this.context, list)
        adapter.notifyDataSetChanged()
        v.recyclerViewNextMatch.adapter = adapter
        v.recyclerViewNextMatch.layoutManager = LinearLayoutManager(this.context)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_next_match, container, false)
        val presenter = MatchPresenter(this, MatchRepo(this, this.context!!))
        presenter.getData("eventsnextleague.php?id=4329")
        v.swipeRefresh.setOnRefreshListener {
            recyclerViewNextMatch.adapter?.notifyDataSetChanged()

            v.swipeRefresh.isRefreshing = false
        }
        return v
    }

}
