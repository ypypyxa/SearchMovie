package com.practiCUM.searchmovie.ui.poster

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.practiCUM.searchmovie.util.Creator
import com.practiCUM.searchmovie.R

class PosterActivity : AppCompatActivity() {

    private val posterController = Creator.providePosterController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poster)
        posterController.onCreate()
    }
}