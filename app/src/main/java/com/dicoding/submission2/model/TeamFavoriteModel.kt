package com.dicoding.submission2.model

data class TeamFavoriteModel(val id: Int?, val teamId: String?) {
    companion object {
        const val ID: String = "ID_"
        const val TABLE_TEAM_FAVORITE: String = "TABLE_TEAM_FAVORITE"
        const val TEAM_ID: String = "TEAM_ID"
    }
}