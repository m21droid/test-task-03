package com.m21droid.booknet.data.datasources.remote.dto

import com.google.gson.annotations.SerializedName

data class LibBookDTO(
    @SerializedName("lib_info")
    val libInfo: LibInfoDTO?,
    val book: BookDTO?,
)