package com.github.khoben.woff2android

import android.graphics.Typeface
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

@RunWith(AndroidJUnit4::class)
class Woff2TypefaceTest {

    private val context = InstrumentationRegistry.getInstrumentation().context

    @Test
    fun createFromAsset() {
        val actual: Typeface =
            Woff2Typeface.createFromAsset(context, context.assets, "lobster.woff2")
        Assert.assertNotEquals(Typeface.DEFAULT, actual)
    }

    @Test
    fun createFromAssetShouldReturnDefaultOnFail() {
        val actual: Typeface = Woff2Typeface.createFromAsset(context, context.assets, "fail")
        Assert.assertEquals(Typeface.DEFAULT, actual)
    }

    @Test
    fun createFromFile() {
        val temp = File.createTempFile("test", null, context.cacheDir)
        context.assets.open("lobster.woff2").copyTo(temp.outputStream())
        val actual: Typeface = Woff2Typeface.createFromFile(context, temp)
        temp.delete()

        Assert.assertNotEquals(Typeface.DEFAULT, actual)
    }

    @Test
    fun createFromFileShouldReturnDefaultOnFail() {
        val actual: Typeface = Woff2Typeface.createFromFile(context, "fail")
        Assert.assertEquals(Typeface.DEFAULT, actual)
    }

    @Test
    fun createFromFilePath() {
        val temp = File.createTempFile("test", null, context.cacheDir)
        context.assets.open("lobster.woff2").copyTo(temp.outputStream())
        val actual: Typeface = Woff2Typeface.createFromFile(context, temp.absolutePath)
        temp.delete()

        Assert.assertNotEquals(Typeface.DEFAULT, actual)
    }

    @Test
    fun createFromFilePathShouldReturnDefaultOnFail() {
        val actual: Typeface = Woff2Typeface.createFromFile(context, File("fail"))
        Assert.assertEquals(Typeface.DEFAULT, actual)
    }
}