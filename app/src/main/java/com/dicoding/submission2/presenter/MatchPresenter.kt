package com.dicoding.submission2.presenter

import com.dicoding.submission2.repository.MatchRepo

class MatchPresenter(private var repo: MatchRepo) {
    fun getData(endpoint: String) {
        repo.getMatch(endpoint)
    }

    fun getSearchData(endpoint: String) {
        repo.getSearch(endpoint)
    }
}