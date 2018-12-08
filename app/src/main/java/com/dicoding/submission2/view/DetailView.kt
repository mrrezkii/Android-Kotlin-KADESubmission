package com.dicoding.submission2.view

import com.dicoding.submission2.model.DetailModelResponse
import com.dicoding.submission2.repository.DetailRepoCallback

interface DetailView : DetailRepoCallback<DetailModelResponse> {
    fun onShowLoading()
    fun onHideLoading()
}