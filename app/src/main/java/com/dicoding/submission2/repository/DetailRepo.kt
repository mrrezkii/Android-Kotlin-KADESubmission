package com.dicoding.submission2.repository

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.dicoding.submission2.R
import com.dicoding.submission2.model.DetailModel
import com.dicoding.submission2.model.DetailModelResponse
import com.dicoding.submission2.model.TeamModel
import com.dicoding.submission2.view.ViewDetail

class DetailRepo(private var view: ViewDetail, private var context: Context) {
    private lateinit var det: DetailModel
    private lateinit var homeTeam: TeamModel
    private lateinit var awayTeam: TeamModel

    companion object {
        private val TAG = DetailRepo::class.java.canonicalName
    }

    fun getDetail(idEvent: String, callback: DetailRepoCallback<DetailModelResponse?>) {
        val queue = Volley.newRequestQueue(context)
        val stringRequest = JsonObjectRequest(
            Request.Method.GET,
            context.resources.getString(R.string.base_url) + "lookupevent.php?id=" + idEvent,
            null,
            Response.Listener { response ->
                val obj = response.getJSONArray("events").getJSONObject(0)
                det = DetailModel(
                    obj.getString("idEvent"),
                    obj.getString("strDate"),
                    obj.getString("strTime"),
                    obj.getString("idHomeTeam"),
                    obj.getString("idAwayTeam"),
                    obj.getString("intHomeScore"),
                    obj.getString("intAwayScore"),
                    obj.getString("strHomeGoalDetails"),
                    obj.getString("strAwayGoalDetails")
                )
                setHomeTeam()
            },
            Response.ErrorListener { error ->
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
            })
        queue.add(stringRequest)
    }

    private fun setHomeTeam() {
        val queue = Volley.newRequestQueue(context)
        val stringRequest = JsonObjectRequest(
            Request.Method.GET,
            context.resources.getString(R.string.base_url) + "lookupteam.php?id=" + det.idHomeTeam,
            null,
            Response.Listener { response ->
                val obj = response.getJSONArray("teams").getJSONObject(0)
                homeTeam = TeamModel(obj.getString("strTeam"), obj.getString("strTeamBadge"))
                setAwayTeam()
            },
            Response.ErrorListener { error ->
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
            })
        queue.add(stringRequest)

    }

    private fun setAwayTeam() {
        val queue = Volley.newRequestQueue(context)
        val stringRequest = JsonObjectRequest(
            Request.Method.GET,
            context.resources.getString(R.string.base_url) + "lookupteam.php?id=" + det.idAwayTeam,
            null,
            Response.Listener { response ->
                val obj = response.getJSONArray("teams").getJSONObject(0)
                awayTeam = TeamModel(obj.getString("strTeam"), obj.getString("strTeamBadge"))
                view.setData(det, homeTeam, awayTeam)
            },
            Response.ErrorListener { error ->
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
            })
        queue.add(stringRequest)

    }
}