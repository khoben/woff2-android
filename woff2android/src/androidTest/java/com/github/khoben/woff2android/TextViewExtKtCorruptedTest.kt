package com.github.khoben.woff2android

import android.graphics.Typeface
import android.widget.TextView
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.File

class TextViewExtKtCorruptedTest : BaseTest() {
    private lateinit var sampleTextView: TextView

    @Before
    fun before() {
        sampleTextView = TextView(context).apply {
            typeface = Typeface.DEFAULT
        }
    }

    @Test
    fun woff2TypefaceAssets() {
        Assert.assertEquals(sampleTextView.typeface, Typeface.DEFAULT)
        sampleTextView.woff2Typeface(assets, "corrupted")
        Assert.assertEquals(sampleTextView.typeface, Typeface.DEFAULT)
    }

    @Test
    fun woff2TypefacePath() {
        Assert.assertEquals(sampleTextView.typeface, Typeface.DEFAULT)
        sampleTextView.woff2Typeface("corrupted")
        Assert.assertEquals(sampleTextView.typeface, Typeface.DEFAULT)
    }

    @Test
    fun woff2TypefaceFile() {
        Assert.assertEquals(sampleTextView.typeface, Typeface.DEFAULT)
        sampleTextView.woff2Typeface(File("corrupted"))
        Assert.assertEquals(sampleTextView.typeface, Typeface.DEFAULT)
    }

    @Test
    fun testWoff2TypefaceBytes() {
        Assert.assertEquals(sampleTextView.typeface, Typeface.DEFAULT)
        sampleTextView.woff2Typeface("corrupted".toByteArray())
        Assert.assertEquals(sampleTextView.typeface, Typeface.DEFAULT)
    }
}