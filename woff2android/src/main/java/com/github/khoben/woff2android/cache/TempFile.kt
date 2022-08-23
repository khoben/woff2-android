package com.github.khoben.woff2android.cache

import java.io.File

interface TempFile {

    /**
     * Creates a new empty temp file
     *
     * @return Temp file
     */
    fun create(): File

    class Cache(private val prefix: String, private val cacheDir: File) : TempFile {
        override fun create(): File {
            return File.createTempFile(prefix, null, cacheDir)
        }
    }
}