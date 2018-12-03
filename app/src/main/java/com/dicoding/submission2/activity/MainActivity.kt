package com.dicoding.submission2.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import com.dicoding.submission2.R
import com.dicoding.submission2.R.id.*
import com.dicoding.submission2.fragment.FavoriteFragment
import com.dicoding.submission2.fragment.MatchFragment
import com.dicoding.submission2.fragment.TeamFragment


class MainActivity : AppCompatActivity() {

    lateinit var toolbar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = supportActionBar!!
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bn_main)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            matches_menu -> {
                toolbar.title = "Match"
                val fl = MatchFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fl_container, fl).commit()
                return@OnNavigationItemSelectedListener true
            }
            teams_menu -> {
                toolbar.title = "Team"
                val fl = TeamFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fl_container, fl).commit()
                return@OnNavigationItemSelectedListener true
            }
            favorites_menu -> {
                toolbar.title = "Favorite"
                val fl = FavoriteFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fl_container, fl).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


}
