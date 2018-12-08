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
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val STR_TEAM: String = "STR_TEAM"
        const val STR_TEAM_BADGE: String = "STR_TEAM_BADGE"
        const val STR_TEAM_DESCRIPTION: String = "STR_TEAM_DESCRIPTION"

    }
}