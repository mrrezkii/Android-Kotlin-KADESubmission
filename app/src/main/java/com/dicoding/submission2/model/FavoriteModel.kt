package com.dicoding.submission2.model

data class FavoriteModel(
    val id: Int?,
    val eventId: String?,
    val dateEvent: String?,
    val homeTeam: String?,
    val awayTeam: String?,
    val homeScore: String?,
    val awayScore: String?
) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val DATE_EVENT = "DATE_EVENT"
        const val HOME_TEAM = "HOME_TEAM"
        const val AWAY_TEAM = "AWAY_TEAM"
        const val HOME_SCORE = "HOME_SCORE"
        const val AWAY_SCORE = "AWAY_SCORE"

    }
}