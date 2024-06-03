package com.practiCUM.searchmovie.ui.poster

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.practiCUM.searchmovie.util.Creator
import com.practiCUM.searchmovie.R
import com.practiCUM.searchmovie.presentation.poster.PosterPresenter
import com.practiCUM.searchmovie.presentation.poster.PosterView

class PosterActivity : AppCompatActivity(), PosterView {

    private lateinit var poster: ImageView
    private lateinit var posterPresenter: PosterPresenter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val url = intent.extras?.getString("poster", "") ?: ""

        posterPresenter = Creator.providePosterPresenter(this, url)

        setContentView(R.layout.activity_poster)
        poster = findViewById(R.id.poster)

        posterPresenter.onCreate()
    }

    override fun setupPoster(url: String) {
        Glide.with(applicationContext)
            .load(url)
            .into(poster)
    }
}