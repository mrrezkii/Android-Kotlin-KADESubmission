package com.dicoding.submission2.view

import com.dicoding.submission2.model.MatchModel

interface ViewAdapter {
    fun showDataRecycler(ls: MutableList<MatchModel>)
}