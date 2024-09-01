package com.practiCUM.searchmovie.ui.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.practiCUM.searchmovie.databinding.ActivityRootBinding

class RootActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Привязываем вёрстку к экрану
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}