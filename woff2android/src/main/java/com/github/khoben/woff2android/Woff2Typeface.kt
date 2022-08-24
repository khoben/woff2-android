package com.github.khoben.woff2android

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Typeface
import com.github.khoben.libwoff2dec.Woff2Decoder
import com.github.khoben.woff2android.cache.Cache
import com.github.khoben.woff2android.cache.DataHash
import com.github.khoben.woff2android.cache.TempFile
import com.tomclaw.cache.DiskLruCache
import java.io.File

/**
 * The Woff2Typeface class specifies the woff2 font file transformation
 * to [Android's Typeface][Typeface]
 *
 * @param context The application's context
 * @param cache Cache implementation
 * @param tempFile TempFile implementation
 */
class Woff2Typeface(
    private val context: Context,
    private val cache: Cache<ByteArray, File>,
    private val tempFile: TempFile
) {

    /**
     * Create a new typeface from the specified woff2 font data.
     * Returns [Typeface.DEFAULT] on any error.
     *
     * @param sourceBytes The byte array of font data
     * @return The new typeface
     */
    fun createFromBytes(sourceBytes: ByteArray): Typeface {
        return try {
            val typefaceFile = cache.get(sourceBytes) ?: tempFile.create().let { tmpFile ->
                Woff2Decoder.decodeBytes(sourceBytes)?.let { decompressedBytes ->
                    tmpFile.outputStream().use { output ->
                        output.write(decompressedBytes)
                    }
                    // renames tmpFile file to internal cache representation
                    cache.put(sourceBytes, tmpFile)
                }
            }
            Typeface.createFromFile(typefaceFile)
        } catch (e: Exception) {
            Typeface.DEFAULT
        }
    }

    /**
     * Create a new typeface from the specified woff2 font data.
     * Returns [Typeface.DEFAULT] on any error.
     *
     * @param mgr  The application's asset manager
     * @param path The file name of the font data in the assets directory
     * @return The new typeface
     */
    fun createFromAsset(mgr: AssetManager, path: String): Typeface {
        return try {
            val sourceBytes = mgr.open(path).use { it.readBytes() }
            return createFromBytes(sourceBytes)
        } catch (e: Exception) {
            Typeface.DEFAULT
        }
    }

    /**
     * Create a new typeface from the specified woff2 font file.
     * Returns [Typeface.DEFAULT] on any error.
     *
     * @param file The path to the font data
     * @return The new typeface
     */
    fun createFromFile(file: File): Typeface {
        return try {
            val sourceBytes = file.inputStream().use { it.readBytes() }
            return createFromBytes(sourceBytes)
        } catch (e: Exception) {
            Typeface.DEFAULT
        }
    }

    /**
     * Create a new typeface from the specified woff2 font file.
     * Returns [Typeface.DEFAULT] on any error.
     *
     * @param path The full path to the font data
     * @return The new typeface
     */
    fun createFromFile(path: String): Typeface {
        return createFromFile(File(path))
    }

    companion object {
        /**
         * Get Woff2Typeface instance
         */
        fun get() = Initializer.get()
    }


    /**
     * Woff2Typeface App Startup Initializer
     */
    class Initializer : androidx.startup.Initializer<Unit> {

        override fun create(context: Context) {
            appContext = context
        }

        override fun dependencies() = emptyList<Class<androidx.startup.Initializer<*>>>()

        companion object {
            private const val DISK_CACHE_CAPACITY_BYTES: Long = 1024 * 1024 * 50 // 50MB

            private lateinit var appContext: Context
            private val instance: Woff2Typeface by lazy {
                Woff2Typeface(
                    context = appContext,
                    cache = Cache.Disk(
                        hash = DataHash.MD5(),
                        cache = DiskLruCache.create(
                            appContext.cacheDir,
                            DISK_CACHE_CAPACITY_BYTES,
                        )
                    ),
                    tempFile = TempFile.Cache(prefix = "woff2", appContext.cacheDir)
                )
            }

            fun get() = instance
        }
    }
}