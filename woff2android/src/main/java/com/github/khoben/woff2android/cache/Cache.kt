package com.github.khoben.woff2android.cache

import com.tomclaw.cache.DiskLruCache
import java.io.File

interface Cache<K, V> {

    fun put(key: K, data: V): V

    fun get(key: K): V?

    fun size(): Int

    fun clear()

    class Disk(
        private val hash: DataHash,
        private val cache: DiskLruCache
    ) : Cache<ByteArray, File> {

        override fun put(key: ByteArray, data: File): File {
            return cache.put(hash.get(key), data)
        }

        override fun get(key: ByteArray): File? {
            return cache.get(hash.get(key))
        }

        override fun size(): Int {
            return cache.keySet().size
        }

        override fun clear() {
            cache.clearCache()
        }
    }
}