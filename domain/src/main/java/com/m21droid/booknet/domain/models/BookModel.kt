package com.m21droid.booknet.domain.models

data class BookModel(
    val type: Int,
    val id: Int,
    val title: String,
    val authorName: String,
    val cover: String,
    val genres: String,
)