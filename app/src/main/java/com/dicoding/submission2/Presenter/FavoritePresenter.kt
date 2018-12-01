package com.dicoding.submission2.Presenter

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.dicoding.submission2.DBHelper
import com.dicoding.submission2.Model.FavoriteModel
import com.dicoding.submission2.Model.MatchModel
import com.dicoding.submission2.R
import com.dicoding.submission2.View.ViewAdapter
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select

class FavoritePresenter(private val context: Context, private val view: ViewAdapter) {
    private val dbHelper = DBHelper(context)
    private val listMatch: MutableList<MatchModel> = mutableListOf()
    fun getData() {
        dbHelper.use {
            val result = select(FavoriteModel.TABLE_FAVORITE).exec { parseList(classParser<FavoriteModel>()) }

            if (result.isEmpty()) {
                view.showDataRecycler(listMatch)
            } else {
                var y = 0
                while (y < result.size) {
                    val queue = Volley.newRequestQueue(context)
                    Log.d(
                        "url",
                        context.resources.getString(R.string.base_url) + "/lookupevent.php?id=" + result[y].eventId
                    )
                    val stringRequest = JsonObjectRequest(Request.Method.GET,
                        context.resources.getString(R.string.base_url) + "/lookupevent.php?id=" + result[y].eventId,
                        null, Response.Listener { response ->
                            val arr = response.getJSONArray("events")
                            val obj = arr.getJSONObject(0)
                            val match = MatchModel(
                                obj.getString("idEvent"),
                                obj.getString("strDate"),
                                obj.getString("strHomeTeam"),
                                obj.getString("strAwayTeam"),
                                obj.getString("intHomeScore"),
                                obj.getString("intAwayScore")
                            )
                            if (match.intHomeScore == "null") match.intHomeScore = ""
                            if (match.intAwayScore == "null") match.intAwayScore = ""
                            listMatch.add(match)
                            if (listMatch.size == result.size) {
                                view.showDataRecycler(listMatch)
                            }
                        }, Response.ErrorListener { error ->
                            Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
                        })
                    queue.add(stringRequest)
                    y = y.inc()
                }
            }
        }
    }

}