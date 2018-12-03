package com.dicoding.submission2

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.dicoding.submission2.model.DetailModel
import com.dicoding.submission2.model.FavoriteModel
import com.dicoding.submission2.model.TeamModel
import com.dicoding.submission2.presenter.DetailPresenter
import com.dicoding.submission2.view.ViewDetail
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.*

class DetailActivity : AppCompatActivity(), ViewDetail {
    private var favorite = false
    private val database = DBHelper(this)
    private lateinit var detail: DetailModel
    private lateinit var home: TeamModel
    private lateinit var away: TeamModel

    override fun setData(det: DetailModel, homeTeam: TeamModel, awayTeam: TeamModel) {
        detail = det
        home = homeTeam
        away = awayTeam
        progressBar.visibility = View.GONE
        tvDate.text = det.dateEvent
        if (!this.isFinishing) {
            Glide.with(this).load(homeTeam.emblem).into(ivHome)
            Glide.with(this).load(awayTeam.emblem).into(ivAway)
        }
        tvHome.text = homeTeam.name
        tvAway.text = awayTeam.name
        if (det.intHomeScore == "null") {
            tvHomeScore.text = "-"
        } else {
            tvHomeScore.text = det.intHomeScore
        }
        if (det.intAwayScore == "null") {
            tvAwayScore.text = "-"
        } else {
            tvAwayScore.text = det.intHomeScore

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        database.use {
            val result = select(FavoriteModel.TABLE_FAVORITE).whereArgs(
                "(" + FavoriteModel.EVENT_ID + ") = {id}",
                "id" to intent.getStringExtra("idEvent")
            ).exec {
                parseList(
                    classParser<FavoriteModel>()
                )
            }
            if (!result.isEmpty()) favorite = true
        }
        val presenter = DetailPresenter(this, intent.getStringExtra("idEvent"), this)
        presenter.getData()
        isAvailable()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.fav_menu, menu)
        if (favorite) {
            menu?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_black_24dp)
        } else {
            menu?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_black_24dp)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_fav -> {
                if (!favorite) {
                    if (addFav()) {
                        item.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_black_24dp)
                    }
                } else {
                    if (removeFav()) {
                        item.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_black_24dp)
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
            database.use {
                insert(
                    FavoriteModel.TABLE_FAVORITE, FavoriteModel.EVENT_ID to intent.getStringExtra("idEvent"),
                    FavoriteModel.DATE_EVENT to detail.dateEvent,
                    FavoriteModel.HOME_TEAM to home.name,
                    FavoriteModel.AWAY_TEAM to away.name,
                    FavoriteModel.HOME_SCORE to detail.intHomeScore,
                    FavoriteModel.AWAY_SCORE to detail.intAwayScore
                )
            }
            favorite = true
            Toast.makeText(this, "Added to favorite", Toast.LENGTH_SHORT).show()
            return true
        }
        return false
    }

    private fun removeFav(): Boolean {
        if (this::detail.isInitialized) {
            database.use {
                delete(
                    FavoriteModel.TABLE_FAVORITE,
                    "(" + FavoriteModel.EVENT_ID + " = {id})",
                    "id" to intent.getStringExtra("idEvent")
                )
            }
            favorite = false
            Toast.makeText(this, "Removed from favorite", Toast.LENGTH_SHORT).show()
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
                if (this is DetailActivity) {
                    return !this.isDestroyed
                } else if (this is DetailActivity) {
                    return !this.isDestroyed
                }
            }
        }
        return true
    }

}
