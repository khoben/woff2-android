package com.github.khoben.libwoff2dec

import android.graphics.Typeface
import org.junit.Assert
import org.junit.Test

class Woff2DecodeTest : BaseTest() {

    @Test
    fun decodeWOFF2() {
        val woff2FontFile = getFileFromAssets("lobster.woff2")
        val outputFontFile = getTempFile()
        val result =
            Woff2Decode.decodeWOFF2(woff2FontFile.absolutePath, outputFontFile.absolutePath)

        Assert.assertTrue(result)

        // test font file
        val actual = Typeface.createFromFile(outputFontFile)
        Assert.assertNotEquals(Typeface.DEFAULT, actual)

        woff2FontFile.delete()
        outputFontFile.delete()
    }

    @Test
    fun decodeWOFF2Byte() {
        val woff2FontFile = getFileFromAssets("lobster.woff2")
        val outputFontBytes = Woff2Decode.decodeWOFF2Byte(woff2FontFile.readBytes())

        Assert.assertNotNull(outputFontBytes)

        // test font bytes
        val tmpFile = getTempFile()
        tmpFile.writeBytes(outputFontBytes!!)
        val actual = Typeface.createFromFile(tmpFile)
        Assert.assertNotEquals(Typeface.DEFAULT, actual)

        tmpFile.delete()
        woff2FontFile.delete()
    }
}