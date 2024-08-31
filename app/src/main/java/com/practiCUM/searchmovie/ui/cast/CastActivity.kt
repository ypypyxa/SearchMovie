package com.practiCUM.searchmovie.ui.cast

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.practiCUM.searchmovie.R
import com.practiCUM.searchmovie.databinding.ActivityCastBinding
import com.practiCUM.searchmovie.ui.cast.models.MoviesCastState
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.core.view.isVisible
import org.koin.core.parameter.parametersOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter


class CastActivity : AppCompatActivity(R.layout.activity_cast) {

    companion object {

        private const val ARGS_MOVIE_ID = "movie_id"

        fun newInstance(context: Context, movieId: String): Intent {
            return Intent(context, CastActivity::class.java).apply {
                putExtra(ARGS_MOVIE_ID, movieId)
            }
        }

    }

    private lateinit var binding : ActivityCastBinding

    // Добавили инжект ViewModel
    private val moviesCastViewModel: MoviesCastViewModel by viewModel {
        parametersOf(intent.getStringExtra(ARGS_MOVIE_ID))
    }

    // Добавили адаптер для RecyclerView
    private val adapter = ListDelegationAdapter(
        movieCastHeaderDelegate(),
        movieCastPersonDelegate(),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCastBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Привязываем адаптер и LayoutManager к RecyclerView
        binding.moviesCastRecyclerView.adapter = adapter
        binding.moviesCastRecyclerView.layoutManager = LinearLayoutManager(this)

        // Наблюдаем за UiState из ViewModel
        moviesCastViewModel.observeState().observe(this) {
            // В зависимости от UiState экрана показываем
            // разные состояния экрана
            when (it) {
                is MoviesCastState.Content -> showContent(it)
                is MoviesCastState.Error -> showError(it)
                is MoviesCastState.Loading -> showLoading()
            }
        }
    }


    private fun showLoading() {
        binding.contentContainer.isVisible = false
        binding.errorMessageTextView.isVisible = false

        binding.progressBar.isVisible = true
    }

    private fun showError(state: MoviesCastState.Error) {
        binding.contentContainer.isVisible = false
        binding.progressBar.isVisible = false

        binding.errorMessageTextView.isVisible = true
        binding.errorMessageTextView.text = state.message
    }

    private fun showContent(state: MoviesCastState.Content) {
        binding.progressBar.isVisible = false
        binding.errorMessageTextView.isVisible = false

        binding.contentContainer.isVisible = true

        // Меняем привязку стейта к UI-элементам
        binding.movieTitle.text = state.fullTitle
        adapter.items = state.items

        adapter.notifyDataSetChanged()
    }

}