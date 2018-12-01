package com.dicoding.submission2.presenter

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.dicoding.submission2.model.MatchModel
import com.dicoding.submission2.view.ViewAdapter
import org.json.JSONArray
import org.json.JSONObject

class Presenter(private var view: ViewAdapter, private var endpoint: String, private var context: Context) {
    private val matchList: MutableList<MatchModel> = mutableListOf()
    fun getData() {
        val queue = Volley.newRequestQueue(context)
        val stringRequestQueue = JsonObjectRequest(
            Request.Method.GET,
            "https://www.thesportsdb.com/api/v1/json/1/" + endpoint,
            null,
            Response.Listener<JSONObject> { response ->

                val arr: JSONArray = response.getJSONArray("events")
                val size = arr.length()
                var x = 0
                while (x < size) {
                    val obj = arr.getJSONObject(x)
                    matchList.add(
                        MatchModel(
                            obj.getString("idEvent"),
                            obj.getString("strDate"),
                            obj.getString("strHomeTeam"),
                            obj.getString("strAwayTeam"),
                            obj.getString("intHomeScore"),
                            obj.getString("intAwayScore")
                        )
                    )
                    x = x.inc()
                }
                view.showDataRecycler(matchList)
            },
            Response.ErrorListener {
                Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
            })
        queue.add(stringRequestQueue)
    }
}