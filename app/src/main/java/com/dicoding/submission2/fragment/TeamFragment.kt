package com.dicoding.submission2.fragment

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.AdapterView
import com.dicoding.submission2.LeagueID
import com.dicoding.submission2.R
import com.dicoding.submission2.adapter.TeamAdapter
import com.dicoding.submission2.model.TeamListModel
import com.dicoding.submission2.presenter.TeamPresenter
import com.dicoding.submission2.repository.TeamRepo
import com.dicoding.submission2.view.ViewTeam
import kotlinx.android.synthetic.main.fragment_team.view.*

class TeamFragment : Fragment(), ViewTeam {
    override fun showDataRecycler(ls: MutableList<TeamListModel>) {
        v.swipeRefresh.isRefreshing = false
        list = ls
        adapter = TeamAdapter(this.context!!, list)
        adapter.notifyDataSetChanged()
        v.recyclerViewTeamList.adapter = adapter
        v.recyclerViewTeamList.layoutManager = LinearLayoutManager(this.context)
    }

    override fun onLoading() {
        list.clear()
        adapter.notifyDataSetChanged()
        v.swipeRefresh.isRefreshing = true
    }

    private var searchView: SearchView? = null
    private lateinit var queryTextListener: SearchView.OnQueryTextListener
    private lateinit var searchItem: MenuItem
    private lateinit var searchManager: SearchManager
    private lateinit var id: Array<String>
    private var loc: Int = 0
    private lateinit var v: View
    private var list: MutableList<TeamListModel> = mutableListOf()
    private lateinit var adapter: TeamAdapter
    private lateinit var presenter: TeamPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_team, container, false)
        setHasOptionsMenu(true)
        id = LeagueID.id.value
        presenter = TeamPresenter(TeamRepo(this, this.context!!))
        presenter.getData("lookup_all_teams.php?id=" + id[loc])
        v.spn_league.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                loc = p2
                presenter.getData("lookup_all_teams.php?id=" + id[loc])
            }

        }

        return v
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search_menu, menu)
        searchItem = menu?.findItem(R.id.action_search) as MenuItem
        searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        searchView = searchItem.actionView as SearchView

        if (searchView != null) {
            searchView?.setSearchableInfo(
                searchManager.getSearchableInfo(activity?.componentName)
            )

            queryTextListener = object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    search(query)
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    search(newText)
                    return true
                }
            }
            searchView?.setOnQueryTextListener(queryTextListener)
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_search ->
                return false
            else -> {
            }
        }
        searchView?.setOnQueryTextListener(queryTextListener)
        return super.onOptionsItemSelected(item)
    }

    fun search(s: String) {
        if (!s.equals("")) {
            //kondisi
        } else {
            //kondisi
        }
    }
}