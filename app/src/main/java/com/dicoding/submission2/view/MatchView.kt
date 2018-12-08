package com.dicoding.submission2.view

import com.dicoding.submission2.model.MatchModelResponse
import com.dicoding.submission2.repository.MatchRepoCallback

interface MatchView : MatchRepoCallback<MatchModelResponse> {
    fun onShowLoading()
    fun onHideLoading()
}