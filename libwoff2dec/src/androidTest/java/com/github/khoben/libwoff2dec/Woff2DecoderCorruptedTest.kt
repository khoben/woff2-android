package com.github.khoben.libwoff2dec

import org.junit.Assert
import org.junit.Test

class Woff2DecoderCorruptedTest : BaseTest() {

    @Test
    fun decodeWOFF2() {
        val woff2FontFile = getFileFromAssets("corrupted.woff2")
        val outputFontFile = getTempFile()
        val result =
            Woff2Decoder.decodeFile(woff2FontFile.absolutePath, outputFontFile.absolutePath)

        Assert.assertFalse(result)

        woff2FontFile.delete()
        outputFontFile.delete()
    }

    @Test
    fun decodeWOFF2Byte() {
        val woff2FontFile = getFileFromAssets("corrupted.woff2")
        val outputFontBytes = Woff2Decoder.decodeBytes(woff2FontFile.readBytes())

        Assert.assertNull(outputFontBytes)

        woff2FontFile.delete()
    }
}