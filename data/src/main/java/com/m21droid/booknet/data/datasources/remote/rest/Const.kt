package com.m21droid.booknet.data.datasources.remote.rest

import android.util.Base64
import java.math.BigInteger
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

typealias DeviceId = String

object Const {

    const val TIMEOUT = 10.toLong()
    const val MAX_SIZE = 4 * 1024 * 1024
    const val HTTP_CACHE = "http-cache"

    const val ENDPOINT = "https://api.booknet.ovh/v1/"
    const val AUTHORIZATION = "Authorization"
    const val BEARER = "Basic bGl0ZGV2OlBUaThleTYydGp5MFVlNg=="
    const val SECRET = "ca749658fabe44b08f2d56b2f4c054c0"
    const val TOKEN = "dtknyWZS7VmiR_mV3DLYRIPYdej8L9ps"

    const val QUERY_APP = "android_booknet"
    const val QUERY_DEBUG = "1"

    const val RESPONSE_CODE = "Response contains incorrect code"
    const val RESPONSE_ERROR = "Response not converted"

}

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}

fun String.decrypt(key: String) =
    try {
        val charset = StandardCharsets.UTF_16LE
        val md = MessageDigest.getInstance("MD5")
        val input = key.toByteArray(charset)
        val digest = md.digest(input)
        val cipher = Cipher.getInstance("AES/CBC/NoPadding").apply {
            val keySpec = SecretKeySpec(digest, "AES")
            val ivSpec = IvParameterSpec(ByteArray(16))
            init(Cipher.DECRYPT_MODE, keySpec, ivSpec)
        }
        val encrypted = Base64.decode(toByteArray(charset), Base64.DEFAULT)
        val decrypted = cipher.doFinal(encrypted)
        String(decrypted, charset)
    } catch (e: Exception) {
        ""
    }