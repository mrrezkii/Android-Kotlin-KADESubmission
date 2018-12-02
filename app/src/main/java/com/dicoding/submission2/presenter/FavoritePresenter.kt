package com.dicoding.submission2.presenter

import com.dicoding.submission2.repository.FavoriteRepo

class FavoritePresenter(private var endpoint: String, private var repo: FavoriteRepo) {
    fun getData() {
        repo.getFavorite(endpoint)
    }
}