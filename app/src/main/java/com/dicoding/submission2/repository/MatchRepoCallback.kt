package com.dicoding.submission2.repository

interface MatchRepoCallback<T> {

    fun onDataLoaded(data: T?)
    fun onDataError()
}