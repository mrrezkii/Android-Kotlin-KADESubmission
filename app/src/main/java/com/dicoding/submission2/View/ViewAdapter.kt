package com.dicoding.submission2.View

import com.dicoding.submission2.Model.MatchModel

interface ViewAdapter {
    fun showDataRecycler(ls: List<MatchModel>)
}