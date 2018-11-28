package com.dicoding.submission2

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.submission2.Model.MatchModel
import kotlinx.android.synthetic.main.recyclerview_layout.view.*

class RecyclerViewAdapter(val context: Context, private var ls: MutableList<MatchModel>) :
    RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_layout, p0, false))
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(data: MatchModel) {
        itemView.tvDate.text = data.dateEvent
        itemView.tvTeamKiri.text = data.strHomeTeam
        itemView.tvSkorKiri.text = data.intHomeScore
        itemView.tvTeamKanan.text = data.strAwayTeam
        itemView.tvSkorKanan.text = data.intAwayScore
        itemView.cvMatch.setOnClickListener {

        }

    }
}
