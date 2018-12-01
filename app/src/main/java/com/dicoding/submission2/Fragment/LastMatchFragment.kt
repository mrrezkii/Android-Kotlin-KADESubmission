package com.dicoding.submission2.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.submission2.Model.MatchModel
import com.dicoding.submission2.Presenter.Presenter

import com.dicoding.submission2.R
import com.dicoding.submission2.RecyclerViewAdapter
import com.dicoding.submission2.View.ViewAdapter
import kotlinx.android.synthetic.main.fragment_last_match.*
import kotlinx.android.synthetic.main.fragment_last_match.view.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class LastMatchFragment : Fragment(), ViewAdapter {
    override fun showDataRecycler(ls: MutableList<MatchModel>) {
        recyclerViewMatch.adapter = RecyclerViewAdapter(this.context!!, ls)
        recyclerViewMatch.adapter!!.notifyDataSetChanged()
        recyclerViewMatch.adapter = recyclerViewMatch.adapter
        recyclerViewMatch.layoutManager = LinearLayoutManager(this.context)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_last_match, container, false)
        val presenter = Presenter(this, "eventspastleague.php?id=4329", this.context!!)
        presenter.getData()
        v.swipeRefresh.setOnRefreshListener {
            recyclerViewMatch.adapter!!.notifyDataSetChanged()

            v.swipeRefresh.isRefreshing = false
        }

        return v
    }


}
