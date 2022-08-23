package com.github.khoben.libwoff2dec

import org.junit.Assert
import org.junit.Test

class Woff2DecodeCorruptedTest : BaseTest() {

    @Test
    fun decodeWOFF2() {
        val woff2FontFile = getFileFromAssets("corrupted.woff2")
        val outputFontFile = getTempFile()
        val result =
            Woff2Decode.decodeWOFF2(woff2FontFile.absolutePath, outputFontFile.absolutePath)

        Assert.assertFalse(result)

        woff2FontFile.delete()
        outputFontFile.delete()
    }

    @Test
    fun decodeWOFF2Byte() {
        val woff2FontFile = getFileFromAssets("corrupted.woff2")
        val outputFontBytes = Woff2Decode.decodeWOFF2Byte(woff2FontFile.readBytes())

        Assert.assertNull(outputFontBytes)

        woff2FontFile.delete()
    }
}