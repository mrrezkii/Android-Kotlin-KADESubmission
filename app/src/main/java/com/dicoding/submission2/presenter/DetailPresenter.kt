package com.dicoding.submission2.presenter

import com.dicoding.submission2.repository.DetailRepo

class DetailPresenter(private val idEvent: String, private var repo: DetailRepo) {
    fun getData() {
        repo.getDetail(idEvent)
    }
}