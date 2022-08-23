package com.github.khoben.woff2android

import android.graphics.Typeface
import org.junit.Assert
import org.junit.Test
import java.io.File

class Woff2TypefaceCorruptedTest : BaseTest() {

    @Test
    fun createFromBytes() {
        val actual: Typeface =
            Woff2Typeface.get().createFromBytes(getBytesFromAssets("corrupted.woff2"))
        Assert.assertEquals(Typeface.DEFAULT, actual)
    }

    @Test
    fun createFromAsset() {
        val actual: Typeface = Woff2Typeface.get().createFromAsset(assets, "corrupted.woff2")
        Assert.assertEquals(Typeface.DEFAULT, actual)
    }

    @Test
    fun createFromFile() {
        val actual: Typeface = Woff2Typeface.get().createFromFile("corrupted.woff2")
        Assert.assertEquals(Typeface.DEFAULT, actual)
    }

    @Test
    fun createFromFilePath() {
        val actual: Typeface = Woff2Typeface.get().createFromFile(File("corrupted.woff2"))
        Assert.assertEquals(Typeface.DEFAULT, actual)
    }
}