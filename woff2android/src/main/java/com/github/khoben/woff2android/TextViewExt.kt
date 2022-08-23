package com.github.khoben.woff2android

import android.content.res.AssetManager
import android.widget.TextView
import java.io.File

/**
 * Set a new typeface from the specified woff2 font file.
 *
 * @param path The full path to the font data
 */
fun TextView.woff2Typeface(path: String) {
    typeface = Woff2Typeface.get().createFromFile(path)
}

/**
 * Set a new typeface from the specified woff2 font file.
 *
 * @param file The path to the font data
 */
fun TextView.woff2Typeface(file: File) {
    typeface = Woff2Typeface.get().createFromFile(file)
}

/**
 * Set a new typeface from the specified woff2 font data.
 *
 * @param mgr  The application's asset manager
 * @param path The file name of the font data in the assets directory
 */
fun TextView.woff2Typeface(mgr: AssetManager, path: String) {
    typeface = Woff2Typeface.get().createFromAsset(mgr, path)
}

/**
 * Set a new typeface from the specified woff2 font data.
 *
 * @param fontByteArray The font byte array data
 */
fun TextView.woff2Typeface(fontByteArray: ByteArray) {
    typeface = Woff2Typeface.get().createFromBytes(fontByteArray)
}