package com.github.khoben.woff2android

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Typeface
import com.github.khoben.libwoff2dec.Woff2Decode
import java.io.File

/**
 * The Woff2Typeface class specifies the woff2 font file transformation
 * to [Android's Typeface][Typeface]
 */
object Woff2Typeface {

    /**
     * Create a new typeface from the specified woff2 font data.
     *
     * @param context The application's context
     * @param mgr  The application's asset manager
     * @param path The file name of the font data in the assets directory
     * @return The new typeface.
     */
    fun createFromAsset(context: Context, mgr: AssetManager, path: String): Typeface {
        return try {
            val inBytes = mgr.open(path).use { Woff2Decode.decodeWOFF2Byte(it.readBytes()) }
            val tmpFile = createTempFile(context)
            tmpFile.writeBytes(inBytes)

            Typeface.createFromFile(tmpFile)
        } catch (e: Exception) {
            Typeface.DEFAULT
        }
    }

    /**
     * Create a new typeface from the specified woff2 font file.
     *
     * @param context The application's context
     * @param file The path to the font data.
     * @return The new typeface.
     */
    fun createFromFile(context: Context, file: File): Typeface {
        return try {
            if (!file.exists()) return Typeface.DEFAULT

            val tmpFile = createTempFile(context)
            Woff2Decode.decodeWOFF2(file.absolutePath, tmpFile.absolutePath)

            Typeface.createFromFile(tmpFile)
        } catch (e: Exception) {
            Typeface.DEFAULT
        }
    }

    /**
     * Create a new typeface from the specified woff2 font file.
     *
     * @param context The application's context
     * @param path The full path to the font data.
     * @return The new typeface.
     */
    fun createFromFile(context: Context, path: String): Typeface {
        return createFromFile(context, File(path))
    }

    private fun createTempFile(context: Context): File {
        return File.createTempFile("woff2", null, context.cacheDir).apply {
            deleteOnExit()
        }
    }
}