package com.github.khoben.woff2android

import android.graphics.Typeface
import org.junit.Assert
import org.junit.Test

class Woff2TypefaceTest : BaseTest() {

    @Test
    fun createFromBytes() {
        val actual: Typeface =
            Woff2Typeface.get().createFromBytes(getBytesFromAssets("lobster.woff2"))
        Assert.assertNotEquals(Typeface.DEFAULT, actual)
    }

    @Test
    fun createFromAsset() {
        val actual: Typeface =
            Woff2Typeface.get().createFromAsset(assets, "lobster.woff2")
        Assert.assertNotEquals(Typeface.DEFAULT, actual)
    }

    @Test
    fun createFromFile() {
        val temp = getFileFromAssets("lobster.woff2")
        val actual: Typeface = Woff2Typeface.get().createFromFile(temp)
        temp.delete()

        Assert.assertNotEquals(Typeface.DEFAULT, actual)
    }

    @Test
    fun createFromFilePath() {
        val temp = getFileFromAssets("lobster.woff2")
        val actual: Typeface = Woff2Typeface.get().createFromFile(temp.absolutePath)
        temp.delete()

        Assert.assertNotEquals(Typeface.DEFAULT, actual)
    }
}