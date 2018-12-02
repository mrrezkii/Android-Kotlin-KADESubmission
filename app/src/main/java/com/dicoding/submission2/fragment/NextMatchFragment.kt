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
import com.dicoding.submission2.presenter.MatchPresenter
import com.dicoding.submission2.repository.MatchRepo
import com.dicoding.submission2.view.ViewAdapter
import kotlinx.android.synthetic.main.fragment_last_match.view.*
import kotlinx.android.synthetic.main.fragment_next_match.*

/**
 * A simple [Fragment] subclass.
 *
 */
class NextMatchFragment : Fragment(), ViewAdapter {
    override fun showDataRecycler(ls: MutableList<MatchModel>) {
        recyclerViewNextMatch.adapter = RecyclerViewAdapter(this.context!!, ls)
        recyclerViewNextMatch.adapter?.notifyDataSetChanged()
        recyclerViewNextMatch.adapter = recyclerViewNextMatch.adapter
        recyclerViewNextMatch.layoutManager = LinearLayoutManager(this.context)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_next_match, container, false)
        val presenter = MatchPresenter("eventsnextleague.php?id=4329", MatchRepo(this, this.context!!))
        presenter.getData()
        v.swipeRefresh.setOnRefreshListener {
            recyclerViewNextMatch.adapter?.notifyDataSetChanged()

            v.swipeRefresh.isRefreshing = false
        }
        return v
    }

}
