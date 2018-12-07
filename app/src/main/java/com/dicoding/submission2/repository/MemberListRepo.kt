package com.dicoding.submission2.repository

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.dicoding.submission2.R
import com.dicoding.submission2.model.MemberModel
import com.dicoding.submission2.view.ViewMemberList

class MemberListRepo(private var view: ViewMemberList, private val context: Context) {
    private val member: MutableList<MemberModel> = mutableListOf()
    fun getData(endpoint: String) {
        val queue = Volley.newRequestQueue(context)
        val stringRequest = JsonObjectRequest(
            Request.Method.GET,
            context.resources.getString(R.string.base_url) + endpoint,
            null,
            Response.Listener { response ->
                val arr = response.getJSONArray("player")
                val size = arr.length()
                var x = 0
                while (x < size) {
                    val obj = arr.getJSONObject(x)
                    val m = MemberModel(
                        obj.getString("idPlayer"),
                        obj.getString("strPlayer"),
                        null,
                        obj.getString("strCutout"),
                        null
                    )
                    member.add(m)
                    x = x.inc()
                }
                view.onDataLoaded(member)
            },
            Response.ErrorListener { error ->
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
            })
        queue.add(stringRequest)
    }

}