package com.dicoding.submission2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.dicoding.submission2.model.FavoriteModel
import org.jetbrains.anko.db.*

class DBHelper(context: Context) : ManagedSQLiteOpenHelper(context, "FavoriteMatch.db") {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0!!.createTable(
            FavoriteModel.TABLE_FAVORITE, true,
            FavoriteModel.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteModel.EVENT_ID to TEXT,
            FavoriteModel.DATE_EVENT to TEXT,
            FavoriteModel.HOME_TEAM to TEXT,
            FavoriteModel.AWAY_TEAM to TEXT,
            FavoriteModel.HOME_SCORE to TEXT,
            FavoriteModel.AWAY_SCORE to TEXT
        )
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.dropTable(FavoriteModel.TABLE_FAVORITE, true)
    }

}