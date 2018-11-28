package com.dicoding.submission2.Presenter

import com.dicoding.submission2.Model.DetailModel
import com.dicoding.submission2.Model.TeamModel

interface ViewDetail {
    fun setData(det: DetailModel, homeTeam: TeamModel, awayTeam: TeamModel)
}