package com.github.khoben.woff2android

import android.content.Context
import android.content.res.AssetManager
import android.widget.TextView
import java.io.File

/**
 * Set a new typeface from the specified woff2 font file.
 *
 * @param context The application's context
 * @param path The full path to the font data.
 */
fun TextView.woff2Typeface(context: Context, path: String) {
    typeface = Woff2Typeface.createFromFile(context, path)
}

/**
 * Set a new typeface from the specified woff2 font file.
 *
 * @param context The application's context
 * @param file The path to the font data.
 */
fun TextView.woff2Typeface(context: Context, file: File) {
    typeface = Woff2Typeface.createFromFile(context, file)
}

/**
 * Set a new typeface from the specified woff2 font data.
 *
 * @param context The application's context
 * @param mgr  The application's asset manager
 * @param path The file name of the font data in the assets directory
 */
fun TextView.woff2Typeface(context: Context, mgr: AssetManager, path: String) {
    typeface = Woff2Typeface.createFromAsset(context, mgr, path)
}