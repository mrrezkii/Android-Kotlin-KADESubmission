package com.dicoding.submission2.repository

import android.content.Context
import com.dicoding.submission2.DBHelper
import com.dicoding.submission2.model.FavoriteModel
import com.dicoding.submission2.model.MatchModel
import com.dicoding.submission2.view.ViewAdapter
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select

class FavoriteRepo(private val context: Context, private val view: ViewAdapter) {
    private val dbHelper = DBHelper(context)
    private val listMatch: MutableList<MatchModel> = mutableListOf()
    fun getFavorite(endpoint: String) {
        dbHelper.use {
            val result = select(FavoriteModel.TABLE_FAVORITE).exec { parseList(classParser<FavoriteModel>()) }

            if (result.isEmpty()) {
                view.showDataRecycler(listMatch)
            } else {
                var y = 0
                while (y < result.size) {
                    listMatch.add(
                        MatchModel(
                            result[y].eventId,
                            result[y].dateEvent,
                            result[y].homeTeam,
                            result[y].awayTeam,
                            result[y].homeScore,
                            result[y].awayScore
                        )
                    )
                    y = y.inc()
                }
                view.showDataRecycler(listMatch)
            }
        }
    }

}