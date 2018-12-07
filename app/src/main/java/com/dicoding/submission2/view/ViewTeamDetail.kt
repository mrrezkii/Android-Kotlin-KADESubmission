package com.dicoding.submission2.view

import com.dicoding.submission2.model.TeamListModel


interface ViewTeamDetail {
    fun onDataLoaded(detail: TeamListModel)
}