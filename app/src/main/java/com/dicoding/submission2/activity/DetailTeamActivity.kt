package com.dicoding.submission2.activity

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import com.dicoding.submission2.R
import com.dicoding.submission2.adapter.DetailTeamPagerAdapter
import com.dicoding.submission2.database.DBHelper
import com.dicoding.submission2.model.TeamFavoriteModel
import com.dicoding.submission2.model.TeamListModel
import com.dicoding.submission2.presenter.TeamDetailPresenter
import com.dicoding.submission2.repository.TeamDetailRepo
import com.dicoding.submission2.view.ViewTeamDetail
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.db.*


class DetailTeamActivity : AppCompatActivity(), ViewTeamDetail {
    lateinit var bundle: Bundle
    private var favorite = false
    private val database = DBHelper(this)
    private lateinit var detail: TeamListModel
    override fun onDataLoaded(detail: TeamListModel) {
        bundle = Bundle()
        bundle.putString("id", detail.id)
        bundle.putString("detail", detail.desc)
        if (!this.isFinishing) {
            Glide.with(this).load(detail.emblem).into(iv_header)
        }
        collapsing_toolbar.title = detail.name
        val adapter = DetailTeamPagerAdapter(supportFragmentManager, bundle)
        viewpager_main.adapter = adapter
        viewpager_main.setOnTouchListener { _, _ ->
            viewpager_main.parent.requestDisallowInterceptTouchEvent(true)
            false
        }
        this.detail = detail
        tabs_main.setupWithViewPager(viewpager_main)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        val teamDetailPresenter = TeamDetailPresenter(intent.getStringExtra("id"), TeamDetailRepo(this, this))
        teamDetailPresenter.getData()
        nested.isFillViewport = true
        try {
            database.use {
                val result = select(TeamFavoriteModel.TABLE_FAVORITE_TEAM).whereArgs(
                    "(" + TeamFavoriteModel.ID + ") = {id}",
                    "id" to intent.getStringExtra("id")
                ).exec {
                    parseList(
                        classParser<TeamFavoriteModel>()
                    )
                }
                if (!result.isEmpty()) favorite = true

            }
        } catch (e: Exception) {
            Toast.makeText(this, e.message!!, Toast.LENGTH_SHORT).show()
        }
        isAvailable()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.fav_menu, menu)
        if (favorite) {
            menu?.getItem(0)?.icon = ContextCompat.getDrawable(
                this,
                R.drawable.ic_favorite_black_24dp
            )
        } else {
            menu?.getItem(0)?.icon = ContextCompat.getDrawable(
                this,
                R.drawable.ic_favorite_border_black_24dp
            )
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_fav -> {
                if (!favorite) {
                    if (addFav()) {
                        item.icon = ContextCompat.getDrawable(
                            this,
                            R.drawable.ic_favorite_black_24dp
                        )
                    }
                } else {
                    if (removeFav()) {
                        item.icon = ContextCompat.getDrawable(
                            this,
                            R.drawable.ic_favorite_border_black_24dp
                        )
                    }
                }
                true
            }
            else -> {
                false
            }
        }
    }

    private fun addFav(): Boolean {
        if (this::detail.isInitialized) {
            try {
                database.use {
                    insert(
                        TeamFavoriteModel.TABLE_FAVORITE_TEAM, TeamFavoriteModel.TEAM_ID to intent.getStringExtra("id"),
                        TeamFavoriteModel.STR_TEAM to detail.name,
                        TeamFavoriteModel.STR_TEAM_BADGE to detail.emblem,
                        TeamFavoriteModel.STR_TEAM_DESCRIPTION to detail.desc
                    )
                }
            } catch (e: Exception) {
                Toast.makeText(this, e.message!!, Toast.LENGTH_SHORT).show()
            }
            favorite = true
            return true
        }
        Toast.makeText(this, "Not Initialized", Toast.LENGTH_SHORT).show()
        return false
    }

    private fun removeFav(): Boolean {
        if (this::detail.isInitialized) {
            database.use {
                delete(
                    TeamFavoriteModel.TABLE_FAVORITE_TEAM,
                    "(" + TeamFavoriteModel.TEAM_ID + " = {id})",
                    "id" to intent.getStringExtra("id")
                )
            }
            favorite = false
            return true
        }
        return false
    }

    @SuppressLint("ObsoleteSdkInt")
    fun Context?.isAvailable(): Boolean {
        if (this == null) {
            return false
        } else if (this !is Application) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (this is DetailTeamActivity) {
                    return !this.isDestroyed
                } else if (this is DetailTeamActivity) {
                    return !this.isDestroyed
                }
            }
        }
        return true
    }
}
