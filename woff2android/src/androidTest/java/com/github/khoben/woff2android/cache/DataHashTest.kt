package com.github.khoben.woff2android.cache

import com.github.khoben.woff2android.BaseTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class DataHashTest(
    private val input: ByteArray,
    private val expected: String
) : BaseTest() {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun parameters(): Iterable<Array<Any>> {
            return listOf(
                arrayOf("some cool data".toByteArray(), "8f73ec8248620142b855403633a6533f"),
                arrayOf(byteArrayOf(), "d41d8cd98f00b204e9800998ecf8427e"),
                arrayOf("some cool data".toByteArray(), "8f73ec8248620142b855403633a6533f"),
                arrayOf("another some cool data".toByteArray(), "cd5efb6d4e15b92769137b5b27d64f42"),
            )
        }
    }

    @Test
    fun testMD5Hash() {
        val hash = DataHash.MD5()
        val actual = hash.get(input)
        Assert.assertEquals(expected, actual)
    }
}