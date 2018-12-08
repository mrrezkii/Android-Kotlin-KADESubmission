package com.dicoding.submission2.repository

interface DetailRepoCallback<T> {

    fun onDataLoaded(data: T?)
    fun onDataError()
}