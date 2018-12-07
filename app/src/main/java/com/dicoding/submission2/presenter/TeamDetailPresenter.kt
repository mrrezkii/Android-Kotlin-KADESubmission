package com.dicoding.submission2.presenter

import com.dicoding.submission2.repository.TeamDetailRepo

class TeamDetailPresenter(val id: String, val repo: TeamDetailRepo) {
    fun getData() {
        repo.getData("lookupteam.php?id=" + id)
    }
}