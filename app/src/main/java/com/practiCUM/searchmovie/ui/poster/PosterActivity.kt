package com.practiCUM.searchmovie.ui.poster

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.practiCUM.searchmovie.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PosterActivity : AppCompatActivity() {

    private lateinit var poster: ImageView
    private val posterViewModel by viewModel<PosterViewModel>() { parametersOf(intent.extras?.getString("poster", "")) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_poster)
        poster = findViewById(R.id.poster)

        posterViewModel.observeSetupPoster().observe(this) {
            setupPoster(it)
        }
    }

    fun setupPoster(url: String) {
        Glide.with(applicationContext)
            .load(url)
            .into(poster)
    }
}