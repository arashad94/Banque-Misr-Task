package com.banquemisr.bmcache

import org.junit.*
import org.junit.Assert.*

class CacheManagerUnitTest {

    private lateinit var cacheManager: CacheManager<String>

    @Before
    fun setup() {
        cacheManager = CacheManager()
    }

    @Test
    fun `EXPECT cache hit WHEN data is cached and within TTL`() {
        cacheManager.put(KEY, DATA)

        val cachedData = cacheManager.get(KEY, TTL)

        assertEquals(DATA, cachedData)
    }

    @Test
    fun `EXPECT cache miss WHEN data is not cached`() {
        val cachedData = cacheManager.get(KEY, TTL)

        assertNull(cachedData)
    }

    @Test
    fun `EXPECT cache is updated WHEN data is added to cache`() {
        cacheManager.put(KEY, DATA)
        val cachedData = cacheManager.get(KEY, TTL)

        assertEquals(DATA, cachedData)
    }

    @Test
    fun `EXPECT all data is removed WHEN cache is cleared`() {
        val key1 = "testKey1"
        val key2 = "testKey2"
        val data1 = "testData1"
        val data2 = "testData2"
        cacheManager.put(key1, data1)
        cacheManager.put(key2, data2)

        cacheManager.clear()
        val cachedData1 = cacheManager.get(key1, TTL)
        val cachedData2 = cacheManager.get(key2, TTL)

        assertNull(cachedData1)
        assertNull(cachedData2)
    }

    private companion object {
        const val TTL = 300_000L
        const val KEY = "testKey"
        const val DATA = "testData"
    }
}
