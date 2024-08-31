package com.practiCUM.searchmovie.ui.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.practiCUM.searchmovie.databinding.ActivityRootBinding
import com.practiCUM.searchmovie.R
import com.practiCUM.searchmovie.ui.movies.MoviesFragment
import androidx.fragment.app.commit

private lateinit var binding: ActivityRootBinding

class RootActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            // Добавляем фрагмент в контейнер
            supportFragmentManager.commit {
                this.add(R.id.rootFragmentContainerView, MoviesFragment())
            }
        }
    }
}