package com.dicoding.submission2.view

import com.dicoding.submission2.model.DetailModel
import com.dicoding.submission2.model.TeamModel

interface ViewDetail {
    fun setData(det: DetailModel, homeTeam: TeamModel, awayTeam: TeamModel)
}