package com.dicoding.submission2.presenter

import com.dicoding.submission2.repository.TeamRepo

class TeamPresenter(private var repo: TeamRepo) {

    fun getData(endpoint: String) {
        repo.getTeam(endpoint)
    }
}