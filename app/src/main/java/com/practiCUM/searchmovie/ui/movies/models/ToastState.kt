package com.practiCUM.searchmovie.ui.movies.models

sealed interface ToastState {
    object None: ToastState
    data class Show(val additionalMessage: String): ToastState
}