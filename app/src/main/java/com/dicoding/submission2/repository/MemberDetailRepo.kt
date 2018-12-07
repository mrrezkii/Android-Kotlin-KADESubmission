package com.dicoding.submission2.repository

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.dicoding.submission2.R
import com.dicoding.submission2.model.MemberModel
import com.dicoding.submission2.view.ViewMemberDetail

class MemberDetailRepo(val context: Context, val view: ViewMemberDetail) {
    fun getData(endpoint: String) {
        val queue = Volley.newRequestQueue(context)
        val stringRequest = JsonObjectRequest(
            Request.Method.GET,
            context.resources.getString(R.string.base_url) + endpoint,
            null,
            Response.Listener { response ->
                val member = response.getJSONArray("players").getJSONObject(0)
                val m = MemberModel(
                    null,
                    member.getString("strPlayer"),
                    member.getString("strDescriptionEN"),
                    null,
                    member.getString("strThumb")
                )
                view.onDataLoaded(m)
            },
            Response.ErrorListener { error ->
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
            })
        queue.add(stringRequest)
    }
}