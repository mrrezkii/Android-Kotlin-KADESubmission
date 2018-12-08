package com.dicoding.submission2.presenter

import com.dicoding.submission2.model.MatchModelResponse
import com.dicoding.submission2.repository.MatchRepo
import com.dicoding.submission2.repository.MatchRepoCallback
import com.dicoding.submission2.view.MatchView

class MatchPresenter(private val view: MatchView, private var repo: MatchRepo) {
    fun getData(endpoint: String) {
        view.onShowLoading()
        repo.getMatch(endpoint, object : MatchRepoCallback<MatchModelResponse?> {
            override fun onDataLoaded(data: MatchModelResponse?) {
                view.onDataLoaded(data)
            }

            override fun onDataError() {
                view.onDataError()
            }
        })
        view.onHideLoading()
    }

    fun getSearchData(endpoint: String) {
        repo.getSearch(endpoint)
    }
}