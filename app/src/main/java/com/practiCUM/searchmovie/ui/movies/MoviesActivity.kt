package com.practiCUM.searchmovie.ui.movies

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.practiCUM.searchmovie.util.Creator
import com.practiCUM.searchmovie.R
import com.practiCUM.searchmovie.presentation.movies.MoviesView
import com.practiCUM.searchmovie.ui.poster.PosterActivity

class MoviesActivity : AppCompatActivity(), MoviesView {

    companion object {
        private const val CLICK_DEBOUNCE_DELAY = 1000L
    }

    private lateinit var queryInput: EditText
    private lateinit var placeholderMessage: TextView
    private lateinit var moviesList: RecyclerView
    private lateinit var progressBar: ProgressBar

    private val adapter = MoviesAdapter {
        if (clickDebounce()) {
            val intent = Intent(this, PosterActivity::class.java)
            intent.putExtra("poster", it.image)
            startActivity(intent)
        }
    }

    private var textWatcher: TextWatcher? = null

    private var isClickAllowed = true

    private val handler = Handler(Looper.getMainLooper())

    private val moviesSearchPresenter = Creator.provideMoviesSearchController(
        adapter = adapter,
        context = this,
        moviesView = this
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        placeholderMessage = findViewById(R.id.placeholderMessage)
        queryInput = findViewById(R.id.queryInput)
        moviesList = findViewById(R.id.locations)
        progressBar = findViewById(R.id.progressBar)

        moviesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        moviesList.adapter = adapter

        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                moviesSearchPresenter.searchDebounce(
                    changedText = s?.toString() ?: ""
                )
            }
            override fun afterTextChanged(s: Editable?) {
            }
        }
        textWatcher?.let { queryInput.addTextChangedListener(it) }

        moviesSearchPresenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        textWatcher?.let { queryInput.removeTextChangedListener(it) }
        moviesSearchPresenter.onDestroy()
    }

    private fun clickDebounce() : Boolean {
        val current = isClickAllowed
        if (isClickAllowed) {
            isClickAllowed = false
            handler.postDelayed({ isClickAllowed = true }, CLICK_DEBOUNCE_DELAY)
        }
        return current
    }

    override fun showPlaceholderMessage(isVisible: Boolean) {
        placeholderMessage.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun showMoviesList(isVisible: Boolean) {
        moviesList.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun showProgressBar(isVisible: Boolean) {
        progressBar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun changePlaceholderText(newPlaceholderText: String) {
        placeholderMessage.text = newPlaceholderText
    }
}