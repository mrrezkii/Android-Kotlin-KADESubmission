package com.dicoding.submission2.presenter

import com.dicoding.submission2.model.DetailModelResponse
import com.dicoding.submission2.repository.DetailRepo
import com.dicoding.submission2.repository.DetailRepoCallback
import com.dicoding.submission2.view.DetailView

class DetailPresenter(private val view: DetailView, private var repo: DetailRepo) {
    fun getData(idEvent: String) {
        view.onShowLoading()
        repo.getDetail(idEvent, object : DetailRepoCallback<DetailModelResponse?> {
            override fun onDataLoaded(data: DetailModelResponse?) {
                view.onDataLoaded(data)
            }

            override fun onDataError() {
                view.onDataError()
            }
        })
        view.onHideLoading()
    }
}