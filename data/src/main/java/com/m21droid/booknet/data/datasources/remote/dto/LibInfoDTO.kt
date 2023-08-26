package com.m21droid.booknet.data.datasources.remote.dto

import com.google.gson.annotations.SerializedName

data class LibInfoDTO(
    @SerializedName("add_date")
    val addDate: Long,
    @SerializedName("last_chr_count")
    val lastChrCount: Int,
    val type: Int,
)