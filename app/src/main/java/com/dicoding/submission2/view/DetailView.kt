package com.dicoding.submission2.view

import com.dicoding.submission2.model.DetailModelResponse
import com.dicoding.submission2.repository.MatchRepoCallback

interface DetailView : MatchRepoCallback<DetailModelResponse> {
    fun onShowLoading()
    fun onHideLoading()
}