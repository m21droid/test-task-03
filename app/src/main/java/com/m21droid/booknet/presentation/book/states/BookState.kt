package com.m21droid.booknet.presentation.book.states

sealed class BookState {

    object Loading : BookState()

    object Failure : BookState()

    object Empty : BookState()

    data class Display(val pages: List<String>) : BookState()

}