package com.m21droid.booknet.data.datasources.remote.mappers

import com.m21droid.booknet.data.datasources.remote.dto.LibBookDTO
import com.m21droid.booknet.domain.models.BookModel

fun LibBookDTO.toBookModel(): BookModel {
    val genres = StringBuilder("")
    val list = book?.genres?.mapNotNull {
        it?.name
    }
    list?.forEachIndexed { index, s ->
        genres.append(s)
        if (index < list.size - 1) {
            genres.append(", ")
        }
    }
    return BookModel(
        type = libInfo?.type ?: 0,
        title = book?.title ?: "",
        authorName = book?.authorName ?: "",
        cover = book?.cover ?: "",
        genres = genres.toString()
    )
}