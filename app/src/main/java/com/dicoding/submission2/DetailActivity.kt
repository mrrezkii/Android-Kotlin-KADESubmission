package com.dicoding.submission2

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

    override fun setData(det: DetailModel, homeTeam: TeamModel, awayTeam: TeamModel) {
        detail = det
        progressBar.visibility = View.GONE
        tvDate.text = det.dateEvent
        Glide.with(this).load(homeTeam.emblem).into(ivHome)
        Glide.with(this).load(awayTeam.emblem).into(ivAway)
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
                    addFav()
                    item.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_black_24dp)
                } else {
                    removeFav()
                    item.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_black_24dp)
                }
                true
            }
            else -> {
                false
            }
        }
    }

    private fun addFav() {
        database.use {
            insert(FavoriteModel.TABLE_FAVORITE, FavoriteModel.EVENT_ID to detail.idEvent)
        }
        favorite = true
        Toast.makeText(this, "Added to favorite", Toast.LENGTH_SHORT).show()
    }

    private fun removeFav() {
        database.use {
            delete(FavoriteModel.TABLE_FAVORITE, "(" + FavoriteModel.EVENT_ID + " = {id})", "id" to detail.idEvent)
        }
        favorite = false
        Toast.makeText(this, "Removed from favorite", Toast.LENGTH_SHORT).show()
    }

}
