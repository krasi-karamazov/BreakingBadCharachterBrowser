package kpk.dev.caching

import javax.inject.Inject

class InMemoryCache<T> @Inject constructor() {
    private val cacheMap: MutableMap<String, T> = mutableMapOf()

    fun get(key: String): T = cacheMap.getValue(key)

    fun put(key: String, obj: T): T = cacheMap.put(key, obj).run { obj }
}