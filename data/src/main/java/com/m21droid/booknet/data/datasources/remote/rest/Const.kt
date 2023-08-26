package com.m21droid.booknet.data.datasources.remote.rest

typealias DeviceId = String

object Const {

    const val TIMEOUT = 10.toLong()
    const val MAX_SIZE = 4 * 1024 * 1024
    const val HTTP_CACHE = "http-cache"

    const val ENDPOINT = "https://api.booknet.ovh/v1/"
    const val AUTHORIZATION = "Authorization"
    const val BEARER = "Basic bGl0ZGV2OlBUaThleTYydGp5MFVlNg=="

    const val QUERY_APP = "android_booknet"
    const val QUERY_DEBUG = "50"

    const val RESPONSE_CODE = "Response contains incorrect code"
    const val RESPONSE_ERROR = "Response not converted"

}