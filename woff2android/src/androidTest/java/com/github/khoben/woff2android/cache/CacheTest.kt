package com.github.khoben.woff2android.cache

import com.github.khoben.woff2android.BaseTest
import com.tomclaw.cache.DiskLruCache
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CacheTest : BaseTest() {

    private val hash = DataHash.MD5()
    private val diskLruCache = DiskLruCache.create(context.cacheDir, 1000L)
    private val cache = Cache.Disk(hash, diskLruCache)

    @Before
    fun before() {
        diskLruCache.clearCache()
        Assert.assertEquals(0, cache.size())
    }

    @After
    fun after() {
        diskLruCache.clearCache()
    }

    @Test
    fun testDiskCache() {
        val tmp = getTempFile().apply { writeText("key_data") }
        val keyData = "key_data".toByteArray()
        cache.put(keyData, tmp)

        val actual = cache.get(keyData)
        Assert.assertNotNull(actual)
        Assert.assertEquals("key_data", actual!!.readText())

        tmp.delete()
    }

    @Test
    fun testDiskCacheEmpty() {
        val keyData = "key_data".toByteArray()
        val actual = cache.get(keyData)
        Assert.assertNull(actual)
    }
}