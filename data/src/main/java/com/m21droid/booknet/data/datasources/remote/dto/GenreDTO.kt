package com.m21droid.booknet.data.datasources.remote.dto

import com.google.gson.annotations.SerializedName

data class GenreDTO(
    val id: Int,
    val name: String?,
    @SerializedName("rating_place")
    val ratingPlace: String?,
)