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
import com.dicoding.submission2.model.MatchModelResponse
import com.dicoding.submission2.presenter.MatchPresenter
import com.dicoding.submission2.repository.MatchRepo
import com.dicoding.submission2.view.MatchView
import com.dicoding.submission2.view.ViewAdapter
import kotlinx.android.synthetic.main.fragment_last_match.view.*


/**
 * A simple [Fragment] subclass.
 *
 */
class LastMatchFragment : Fragment(), MatchView, ViewAdapter {
    override fun onShowLoading() {

    }

    override fun onHideLoading() {

    }

    override fun onDataLoaded(data: MatchModelResponse?) {

    }

    override fun onDataError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoading() {
        list.clear()

        adapter.notifyDataSetChanged()

        v.swipeRefresh.isRefreshing = true
    }

    private var list: MutableList<MatchModel> = mutableListOf()
    private lateinit var v: View
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var id: Array<String>
    private var loc: Int = 0
    override fun showDataRecycler(ls: MutableList<MatchModel>) {
        v.swipeRefresh.isRefreshing = false
        list = ls
        adapter = RecyclerViewAdapter(this.context, list)
        adapter.notifyDataSetChanged()
        v.recyclerViewLastMatch.adapter = adapter
        v.recyclerViewLastMatch.layoutManager = LinearLayoutManager(this.context)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_last_match, container, false)
        adapter = RecyclerViewAdapter(this.context!!, list)
        id = LeagueID.id.value
        val presenter = MatchPresenter(this, MatchRepo(this, this.context!!))
        presenter.getData("eventspastleague.php?id=" + id[loc])
        v.swipeRefresh.setOnRefreshListener {
            onLoading()
            presenter.getData("eventspastleague.php?id=" + id[loc])
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
