package com.github.khoben.woff2android.cache

import java.security.MessageDigest

interface DataHash {

    fun get(byte: ByteArray): String

    class MD5(
        private val md5: MessageDigest = MessageDigest.getInstance("md5")
    ) : DataHash {

        override fun get(byte: ByteArray): String {
            return md5.digest(byte).joinToString("") { "%02x".format(it) }
        }
    }
}