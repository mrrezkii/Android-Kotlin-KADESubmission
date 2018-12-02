package com.dicoding.submission2.presenter

import com.dicoding.submission2.repository.MatchRepo

class MatchPresenter(private var endpoint: String, private var repo: MatchRepo) {
    fun getData() {
        repo.getMatch(endpoint)
    }
}