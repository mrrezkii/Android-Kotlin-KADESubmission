package com.dicoding.submission2.presenter

import com.dicoding.submission2.repository.DetailRepo

class DetailPresenter(private var endpoint: String, private var repo: DetailRepo) {
    fun getData() {
        repo.getDetail(endpoint)
    }
}