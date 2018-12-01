package com.dicoding.submission2

import android.content.Context
import android.content.Intent
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

    override fun getItemCount(): Int = ls.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) = p0.bind(ls[p1])

}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(data: MatchModel) {
        itemView.tvDate.text = data.dateEvent
        itemView.tvTeamKiri.text = data.strHomeTeam

        if (data.intHomeScore == "null") {
            itemView.tvSkorKiri.text = "-"
        } else {
            itemView.tvSkorKiri.text = data.intHomeScore

        }
        itemView.tvTeamKanan.text = data.strAwayTeam

        if (data.intHomeScore == "null") {
            itemView.tvSkorKanan.text = "-"
        } else {
            itemView.tvSkorKanan.text = data.intAwayScore

        }
        itemView.cvMatch.setOnClickListener {
            val DetailIntent = Intent(itemView.context, DetailActivity::class.java)
            DetailIntent.putExtra("idEvent", data.idEvent)
            DetailIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            itemView.context.startActivity(DetailIntent)
        }

    }
}
