package com.dicoding.submission2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.dicoding.submission2.model.MatchFavoriteModel
import com.dicoding.submission2.model.TeamFavoriteModel
import org.jetbrains.anko.db.*

class DBHelper(context: Context) : ManagedSQLiteOpenHelper(context, "FavoriteMatch.db") {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0!!.createTable(
            MatchFavoriteModel.TABLE_FAVORITE, true,
            MatchFavoriteModel.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            MatchFavoriteModel.EVENT_ID to TEXT,
            MatchFavoriteModel.DATE_EVENT to TEXT,
            MatchFavoriteModel.HOME_TEAM to TEXT,
            MatchFavoriteModel.AWAY_TEAM to TEXT,
            MatchFavoriteModel.HOME_SCORE to TEXT,
            MatchFavoriteModel.AWAY_SCORE to TEXT
        )
        p0.createTable(
            TeamFavoriteModel.TABLE_FAVORITE_TEAM, true,
            TeamFavoriteModel.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            TeamFavoriteModel.TEAM_ID to TEXT,
            TeamFavoriteModel.STR_TEAM to TEXT,
            TeamFavoriteModel.STR_TEAM_BADGE to TEXT,
            TeamFavoriteModel.STR_TEAM_DESCTIPTION to TEXT
        )
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.dropTable(MatchFavoriteModel.TABLE_FAVORITE, true)
        p0.dropTable(TeamFavoriteModel.TABLE_FAVORITE_TEAM, true)
    }

}