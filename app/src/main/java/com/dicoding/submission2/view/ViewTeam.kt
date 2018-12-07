package com.dicoding.submission2.view

import com.dicoding.submission2.model.TeamListModel

interface ViewTeam {
    fun showDataRecycler(ls: MutableList<TeamListModel>)
    fun onLoading()
}