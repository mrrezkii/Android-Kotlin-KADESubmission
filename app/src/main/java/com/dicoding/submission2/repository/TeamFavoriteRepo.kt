package com.dicoding.submission2.repository

import android.content.Context
import com.dicoding.submission2.DBHelper
import com.dicoding.submission2.model.TeamFavoriteModel
import com.dicoding.submission2.model.TeamListModel
import com.dicoding.submission2.view.ViewTeam
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select

class TeamFavoriteRepo(private val context: Context, private val view: ViewTeam) {
    private val dbHelper = DBHelper(context)
    private val listTeam: MutableList<TeamListModel> = mutableListOf()
    fun getTeamFavorite() {
        dbHelper.use {
            val result =
                select(TeamFavoriteModel.TABLE_FAVORITE_TEAM).exec { parseList(classParser<TeamFavoriteModel>()) }

            if (result.isEmpty()) {
                view.showDataRecycler(listTeam)
            } else {
                var y = 0
                while (y < result.size) {
                    listTeam.add(
                        TeamListModel(
                            result[y].teamId,
                            result[y].strTeam,
                            result[y].strTeamBadge,
                            result[y].strDescriptionEN
                        )
                    )
                    y = y.inc()
                }
                view.showDataRecycler(listTeam)
            }
        }
    }

}