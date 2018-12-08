package com.dicoding.submission2.presenter

import com.dicoding.submission2.repository.TeamFavoriteRepo


class TeamFavoritePresenter(private var repo: TeamFavoriteRepo) {
    fun getData() {
        repo.getTeamFavorite()
    }
}