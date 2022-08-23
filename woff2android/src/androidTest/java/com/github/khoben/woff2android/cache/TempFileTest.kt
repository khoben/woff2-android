package com.github.khoben.woff2android.cache

import com.github.khoben.woff2android.BaseTest
import org.junit.Assert
import org.junit.Test

class TempFileTest : BaseTest() {

    @Test
    fun testCreateTempFile() {
        val tempFile = TempFile.Cache("test", context.cacheDir)
        val tmp = tempFile.create()
        Assert.assertTrue(tmp.exists())
        tmp.delete()
    }
}