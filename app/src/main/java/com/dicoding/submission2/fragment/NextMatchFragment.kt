package com.dicoding.submission2.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.dicoding.submission2.LeagueID
import com.dicoding.submission2.R
import com.dicoding.submission2.adapter.RecyclerViewAdapter
import com.dicoding.submission2.model.MatchModel
import com.dicoding.submission2.presenter.MatchPresenter
import com.dicoding.submission2.repository.MatchRepo
import com.dicoding.submission2.view.ViewAdapter
import kotlinx.android.synthetic.main.fragment_next_match.*
import kotlinx.android.synthetic.main.fragment_next_match.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class NextMatchFragment : Fragment(), ViewAdapter {
    private var list: MutableList<MatchModel> = mutableListOf()
    private lateinit var v: View
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var id: Array<String>
    private var loc: Int = 0
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
        adapter = RecyclerViewAdapter(this.context!!, list)
        id = LeagueID.id.value
        val presenter = MatchPresenter(MatchRepo(this, this.context!!))
        presenter.getData("eventsnextleague.php?id=4328")
        v.swipeRefresh.setOnRefreshListener {
            recyclerViewNextMatch.adapter?.notifyDataSetChanged()

            v.swipeRefresh.isRefreshing = false
        }
        v.spn_league.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                loc = p2
                presenter.getData("eventspastleague.php?id=" + id[loc])
            }

        }

        return v
    }

}
