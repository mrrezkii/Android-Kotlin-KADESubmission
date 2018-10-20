package com.dicoding.hellokotlin.AnkoUILayout

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import org.jetbrains.anko.*

class ListItemUI : AnkoComponent<Context> {
    lateinit var image: ImageView
    lateinit var name: TextView

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            orientation = LinearLayout.HORIZONTAL
            padding = dip(16)

            imageView {
                id = Ids.image
            }.lparams(width = dip(50), height = dip(50))
            textView {
                id = Ids.name
            }.lparams(width = wrapContent, height = wrapContent) {
                gravity = Gravity.CENTER_VERTICAL
                margin = dip(10)
            }
        }

    }

    companion object Ids {
        val image = 1
        val name = 2
    }

}