package com.banquemisr.bmcache

class CacheManager<T> {

    private val cache = mutableMapOf<String, CacheEntry<T>>()

    fun get(key: String, ttl: Long): T? {
        val entry = cache[key]
        return if (entry != null && System.currentTimeMillis() - entry.timestamp < ttl) {
            entry.data
        } else {
            cache.remove(key)
            null
        }
    }

    fun put(key: String, data: T) {
        cache[key] = CacheEntry(data, System.currentTimeMillis())
    }

    fun clear() {
        cache.clear()
    }
}
