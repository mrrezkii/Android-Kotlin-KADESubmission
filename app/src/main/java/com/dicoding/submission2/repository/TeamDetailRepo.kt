package com.dicoding.submission2.repository

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.dicoding.submission2.R
import com.dicoding.submission2.model.TeamListModel
import com.dicoding.submission2.view.ViewTeamDetail

class TeamDetailRepo(private var view: ViewTeamDetail, private val context: Context) {
    fun getData(endpoint: String) {
        val queue = Volley.newRequestQueue(context)
        val stringRequest = JsonObjectRequest(
            Request.Method.GET,
            context.resources.getString(R.string.base_url) + endpoint,
            null,
            Response.Listener { response ->
                val team = response.getJSONArray("teams").getJSONObject(0)
                val t = TeamListModel(
                    team.getString("idTeam"),
                    team.getString("strTeam"),
                    team.getString("strTeamBadge"),
                    team.getString("strDescriptionEN")
                )
                view.onDataLoaded(t)
            },
            Response.ErrorListener { error ->
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
            })
        queue.add(stringRequest)
    }
}