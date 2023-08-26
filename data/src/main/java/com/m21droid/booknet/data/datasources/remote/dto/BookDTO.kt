package com.m21droid.booknet.data.datasources.remote.dto

import com.google.gson.annotations.SerializedName

data class BookDTO(
    val id: Int,
    val title: String?,
    @SerializedName("author_id")
    val authorId: Int,
    @SerializedName("author_name")
    val authorName: String?,
    @SerializedName("author_avatar_url")
    val authorAvatarUrl: String?,
    @SerializedName("co_author")
    val coAuthor: Int,
    @SerializedName("co_author_name")
    val coAuthorName: String?,
    @SerializedName("co_author_avatar_url")
    val coAuthorAvatarUrl: String?,
    @SerializedName("pub_id")
    val pubId: Int,
    @SerializedName("pub_name")
    val pubName: String?,
    val type: String?,
    @SerializedName("bonus_balance_promocode")
    val bonusBalancePromocode: Boolean,
    val genres: List<GenreDTO?>?,
    val tags: List<TagDTO?>?,
    val annotation: String?,
    val cover: String?,
    @SerializedName("created_at")
    val createdAt: Long,
    @SerializedName("finished_at")
    val finishedAt: Long,
    @SerializedName("last_update")
    val lastUpdate: Long,
    @SerializedName("adult_only")
    val adultOnly: Boolean,
    @SerializedName("book_active")
    val bookActive: Boolean,
    val intro: Boolean,
    @SerializedName("free_chapters")
    val freeChapters: Int,
    val rating: Int,
    val likes: Int,
    val rewards: Int,
    val views: Int,
    @SerializedName("in_libraries")
    val inLibraries: Int,
    val comments: Int,
    @SerializedName("allow_comments")
    val allowComments: Boolean,
    @SerializedName("text_to_speech_allowed")
    val textToSpeechAllowed: Boolean,
    @SerializedName("total_chr_length")
    val totalChrLength: Int,
    val pages: Int,
    val price: Double,
    val blocked: Boolean,
    val url: String?,
    val liked: Boolean,
    val rewarded: Boolean,
    val status: String?,
    @SerializedName("is_purchased")
    val isPurchased: Boolean,
    val lang: String?,
    @SerializedName("currency_code")
    val currencyCode: String?,
    @SerializedName("author_books_count")
    val authorBooksCount: Int,
    @SerializedName("annotaco_author_books_counttion")
    val annotacoAuthorBooksCounttion: Int,
    @SerializedName("cycle_id")
    val cycleId: Int,
    @SerializedName("priority_cycle")
    val priorityCycle: Int,
    val booktrailer: String?,
    @SerializedName("tmp_access_sold")
    val tmpAccessSold: String?,
    val publisher: String?,
    @SerializedName("selling_frozen")
    val sellingFrozen: Boolean,
    val hidden: Boolean,
    @SerializedName("has_audio")
    val hasAudio: Boolean,
)