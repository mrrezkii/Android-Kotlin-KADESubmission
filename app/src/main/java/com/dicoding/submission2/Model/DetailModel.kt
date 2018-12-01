package com.dicoding.submission2.Model

data class DetailModel(
    var dateEvent: String? = null,
    var strTime: String? = null,
    var idHomeTeam: String? = null,
    var idAwayTeam: String? = null,
    var intHomeScore: String? = null,
    var intAwayScore: String? = null,
    var strHomeGoalDetails: String,
    var strAwayGoalDetails: String

)