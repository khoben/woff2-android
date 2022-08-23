package com.github.khoben.woff2android

import android.graphics.Typeface
import org.junit.Assert
import org.junit.Test
import java.io.File

class Woff2TypefaceNotFoundTest : BaseTest() {

    @Test
    fun createFromBytes() {
        val actual: Typeface =
            Woff2Typeface.get().createFromBytes(byteArrayOf())
        Assert.assertEquals(Typeface.DEFAULT, actual)
    }

    @Test
    fun createFromAsset() {
        val actual: Typeface = Woff2Typeface.get().createFromAsset(assets, "fail")
        Assert.assertEquals(Typeface.DEFAULT, actual)
    }

    @Test
    fun createFromFile() {
        val actual: Typeface = Woff2Typeface.get().createFromFile("fail")
        Assert.assertEquals(Typeface.DEFAULT, actual)
    }

    @Test
    fun createFromFilePath() {
        val actual: Typeface = Woff2Typeface.get().createFromFile(File("fail"))
        Assert.assertEquals(Typeface.DEFAULT, actual)
    }
}