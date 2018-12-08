package com.dicoding.submission2.model

data class TeamFavoriteModel(
    val id: Int?,
    val teamId: String?,
    val strTeam: String?,
    val strTeamBadge: String?,
    val strDescriptionEN: String?
) {

    companion object {
        const val TABLE_FAVORITE_TEAM: String = "TABLE_FAVORITE_TEAM"
        const val ID: String = "ID"
        const val TEAM_ID: String = "TEAM_ID"
        const val STR_TEAM = "STR_TEAM"
        const val STR_TEAM_BADGE = "STR_TEAM_BADGE"
        const val STR_TEAM_DESCTIPTION = "STR_TEAM_DESCTIPTION"

    }
}