package com.github.khoben.woff2android

import android.graphics.Typeface
import android.widget.TextView
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TextViewExtKtTest : BaseTest() {

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
        sampleTextView.woff2Typeface(assets, "lobster.woff2")
        Assert.assertNotEquals(sampleTextView.typeface, Typeface.DEFAULT)
    }

    @Test
    fun testWoff2TypefacePath() {
        Assert.assertEquals(sampleTextView.typeface, Typeface.DEFAULT)
        val tmp = getFileFromAssets("lobster.woff2")
        sampleTextView.woff2Typeface(tmp.absolutePath)
        Assert.assertNotEquals(sampleTextView.typeface, Typeface.DEFAULT)
        tmp.delete()
    }

    @Test
    fun testWoff2TypefaceFile() {
        Assert.assertEquals(sampleTextView.typeface, Typeface.DEFAULT)
        val tmp = getFileFromAssets("lobster.woff2")
        sampleTextView.woff2Typeface(tmp)
        Assert.assertNotEquals(sampleTextView.typeface, Typeface.DEFAULT)
        tmp.delete()
    }

    @Test
    fun testWoff2TypefaceBytes() {
        Assert.assertEquals(sampleTextView.typeface, Typeface.DEFAULT)
        val tmp = getBytesFromAssets("lobster.woff2")
        sampleTextView.woff2Typeface(tmp)
        Assert.assertNotEquals(sampleTextView.typeface, Typeface.DEFAULT)
    }
}