package com.dicoding.hellokotlin.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.dicoding.hellokotlin.Adapter.RecyclerViewAdapter
import com.dicoding.hellokotlin.AnkoUILayout.MainActivityUI
import com.dicoding.hellokotlin.ListItem.Item
import com.dicoding.hellokotlin.R
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    lateinit var mainActivity: MainActivityUI

    private var items: MutableList<Item> = mutableListOf()

    private fun initData() {
        val clubName = resources.getStringArray(R.array.club_name)
        val clubBadge = resources.obtainTypedArray(R.array.club_image)
        items.clear()
        for (i in clubName.indices) {
            items.add(Item(clubName[i], clubBadge.getResourceId(i, 0)))
        }
        clubBadge.recycle()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivity = MainActivityUI()
        mainActivity.setContentView(this)

        initData()
        mainActivity.recyclerView.layoutManager = LinearLayoutManager(this)
        mainActivity.recyclerView.adapter = RecyclerViewAdapter(this, items) {
            startActivity<DetailActivity>("INDEX_CLUB" to items.indexOf(it))
        }
    }
}
