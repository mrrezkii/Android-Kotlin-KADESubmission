package com.dicoding.submission2.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.dicoding.submission2.R
import com.dicoding.submission2.activity.MemberDetailActivity
import com.dicoding.submission2.model.MemberModel
import kotlinx.android.synthetic.main.recyclerviewteam_layout.view.*

class MemberAdapter(private val context: Context, private val memberList: MutableList<MemberModel>) :
    RecyclerView.Adapter<MemberAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerviewteam_layout, p0, false))
    }

    override fun getItemCount(): Int = memberList.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindItem(memberList[p1])
    }

    inner class ViewHolder(val v: View) : RecyclerView.ViewHolder(v) {
        fun bindItem(member: MemberModel) {
            Glide.with(v.context).load(member.cutOut).into(v.ivList)
            v.tvName.text = member.name
            v.layout.setOnClickListener {
                val intent = Intent(v.context!!, MemberDetailActivity::class.java)
                intent.putExtra("id",member.id)
                v.context!!.startActivity(intent)
            }
        }
    }
}