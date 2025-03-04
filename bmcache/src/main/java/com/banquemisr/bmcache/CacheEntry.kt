package com.banquemisr.bmcache

data class CacheEntry<T>(
    val data: T,
    val timestamp: Long
)
