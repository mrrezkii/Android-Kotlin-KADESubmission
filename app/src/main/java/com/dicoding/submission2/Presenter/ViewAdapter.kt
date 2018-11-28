package com.dicoding.submission2.Presenter

import com.dicoding.submission2.Model.MatchModel

interface ViewAdapter {
    fun showDataRecycler(ls: List<MatchModel>)
}