package com.github.khoben.libwoff2dec

import org.junit.Assert
import org.junit.Test

class Woff2DecodeNotFoundTest : BaseTest() {

    @Test
    fun decodeWOFF2() {
        val outputFontFile = getTempFile()
        val result =
            Woff2Decode.decodeWOFF2("failed", outputFontFile.absolutePath)

        Assert.assertFalse(result)

        outputFontFile.delete()
    }

    @Test
    fun decodeWOFF2Byte() {
        val outputFontBytes = Woff2Decode.decodeWOFF2Byte(byteArrayOf())

        Assert.assertNull(outputFontBytes)
    }
}