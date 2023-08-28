package com.m21droid.booknet.presentation.book.states

import com.m21droid.booknet.domain.models.PageModel

sealed class BookState {

    object Loading : BookState()

    object Failure : BookState()

    object Empty : BookState()

    data class Display(val pages: List<PageModel>) : BookState()

}