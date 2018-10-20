package com.dicoding.hellokotlin.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.hellokotlin.R
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class DetailActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        info(intent.getIntExtra("INDEX_CLUB", 0))

        val clubIndex = intent.getIntExtra("INDEX_CLUB", 0)

        val clubName = resources.getStringArray(R.array.club_name)
        val clubBadge = resources.obtainTypedArray(R.array.club_image)
        val clubDescription = resources.getStringArray(R.array.club_description)

        Glide.with(this).load(clubBadge.getResourceId(clubIndex, 0)).into(imageViewBadge)
        textViewName.text = clubName[clubIndex]
        textViewDescription.text = clubDescription[clubIndex]

    }
}
