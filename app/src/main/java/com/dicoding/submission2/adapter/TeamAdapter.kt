package com.dicoding.submission2.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.dicoding.submission2.R
import com.dicoding.submission2.model.TeamListModel
import kotlinx.android.synthetic.main.recyclerviewteam_layout.view.*

class TeamAdapter(val con: Context, val team: MutableList<TeamListModel>) :
    RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(con).inflate(R.layout.recyclerviewteam_layout, p0, false))
    }

    override fun getItemCount(): Int = team.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindItem(team[p1])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(team: TeamListModel) {
            itemView.tvName.text = team.name
            Glide.with(itemView).load(team.emblem).into(itemView.ivList)
        }
    }
}