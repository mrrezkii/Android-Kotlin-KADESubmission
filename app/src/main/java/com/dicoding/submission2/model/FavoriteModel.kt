package com.dicoding.submission2.model

data class FavoriteModel(val id: Int?, val eventId: String?) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"

    }
}