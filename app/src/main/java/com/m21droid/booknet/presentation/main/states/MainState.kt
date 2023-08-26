package com.m21droid.booknet.presentation.main.states

import com.m21droid.booknet.domain.models.BookModel

sealed class MainState {

    object Loading : MainState()

    object Failure : MainState()

    object Empty : MainState()

    data class Display(val books: List<BookModel>) : MainState()

}