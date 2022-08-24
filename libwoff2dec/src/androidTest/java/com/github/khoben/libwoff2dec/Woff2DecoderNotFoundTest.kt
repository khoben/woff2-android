package com.github.khoben.libwoff2dec

import org.junit.Assert
import org.junit.Test

class Woff2DecoderNotFoundTest : BaseTest() {

    @Test
    fun decodeWOFF2() {
        val outputFontFile = getTempFile()
        val result =
            Woff2Decoder.decodeFile("failed", outputFontFile.absolutePath)

        Assert.assertFalse(result)

        outputFontFile.delete()
    }

    @Test
    fun decodeWOFF2Byte() {
        val outputFontBytes = Woff2Decoder.decodeBytes(byteArrayOf())

        Assert.assertNull(outputFontBytes)
    }
}