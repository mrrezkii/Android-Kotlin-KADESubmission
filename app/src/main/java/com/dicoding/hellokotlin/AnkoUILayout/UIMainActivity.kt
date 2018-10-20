package com.dicoding.hellokotlin.AnkoUILayout

import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.dicoding.hellokotlin.Activity.MainActivity
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivityUI : AnkoComponent<MainActivity> {

    lateinit var recyclerView: RecyclerView

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        linearLayout {
            orientation = LinearLayout.VERTICAL
            recyclerView = recyclerView {
                lparams(width = matchParent, height = matchParent)
            }

        }
    }
}