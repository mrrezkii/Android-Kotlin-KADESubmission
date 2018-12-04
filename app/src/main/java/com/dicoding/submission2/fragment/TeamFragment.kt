package com.dicoding.submission2.fragment

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import android.view.*
import com.dicoding.submission2.R

class TeamFragment : Fragment() {
    private var searchView: SearchView? = null
    private lateinit var queryTextListener: SearchView.OnQueryTextListener
    private lateinit var searchItem: MenuItem
    private lateinit var searchManager: SearchManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_team, container, false)
        setHasOptionsMenu(true)

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