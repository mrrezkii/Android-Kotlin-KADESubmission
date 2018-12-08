package com.dicoding.submission2.fragment

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import com.dicoding.submission2.R
import com.dicoding.submission2.adapter.MatchPagerAdapter
import com.dicoding.submission2.adapter.RecyclerViewAdapter
import com.dicoding.submission2.model.MatchModel
import com.dicoding.submission2.model.MatchModelResponse
import com.dicoding.submission2.presenter.MatchPresenter
import com.dicoding.submission2.repository.MatchRepo
import com.dicoding.submission2.view.MatchView
import com.dicoding.submission2.view.ViewAdapter
import kotlinx.android.synthetic.main.fragment_match.*
import kotlinx.android.synthetic.main.fragment_match.view.*

class MatchFragment : Fragment(), MatchView, ViewAdapter {
    override fun onShowLoading() {

    }

    override fun onHideLoading() {

    }

    override fun onDataLoaded(data: MatchModelResponse?) {

    }

    override fun onDataError() {

    }

    override fun onLoading() {

    }

    override fun showDataRecycler(ls: MutableList<MatchModel>) {
        recyclerMatch.adapter = RecyclerViewAdapter(this.context, ls)
        recyclerMatch.layoutManager = LinearLayoutManager(this.context)
    }

    private var searchView: SearchView? = null
    private lateinit var queryTextListener: SearchView.OnQueryTextListener
    private lateinit var searchItem: MenuItem
    private lateinit var searchManager: SearchManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_match, container, false)

        val adapter = MatchPagerAdapter(childFragmentManager)
        setHasOptionsMenu(true)
        v.viewpager_main.adapter = adapter
        v.tabs_main.setupWithViewPager(v.viewpager_main)
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
            tabs_main.visibility = View.INVISIBLE
            viewpager_main.visibility = View.INVISIBLE
            recyclerMatch.visibility = View.VISIBLE
            val presenter = MatchPresenter(this, MatchRepo(this, this.context!!))
            presenter.getSearchData("searchevents.php?e=" + s)
        } else {
            tabs_main.visibility = View.VISIBLE
            viewpager_main.visibility = View.VISIBLE
        }
    }
}