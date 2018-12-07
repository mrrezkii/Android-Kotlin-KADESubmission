package com.dicoding.submission2.repository

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.dicoding.submission2.R
import com.dicoding.submission2.model.TeamListModel
import com.dicoding.submission2.view.ViewTeam

class TeamRepo(private var view: ViewTeam, private val context: Context) {
    private val teamList: MutableList<TeamListModel> = mutableListOf()
    fun getTeam(endpoint: String) {
        val queue = Volley.newRequestQueue(context)
        val stringRequest = JsonObjectRequest(
            Request.Method.GET,
            context.resources.getString(R.string.base_url) + endpoint,
            null,
            Response.Listener { response ->
                val arr = response.getJSONArray("teams")
                val size = arr.length()
                var x = 0
                while (x < size) {
                    val obj = arr.getJSONObject(x)
                    val team = TeamListModel(
                        obj.getString("idTeam"),
                        obj.getString("strTeam"),
                        obj.getString("strTeamBadge"),
                        obj.getString("strDescriptionEN")
                    )
                    teamList.add(team)
                    x = x.inc()
                }
                view.showDataRecycler(teamList)
            },
            Response.ErrorListener { error ->
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
            })
        queue.add(stringRequest)
    }
}