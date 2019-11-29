package kpk.dev.caching

class InMemoryCache<T> {
    private val cacheMap: MutableMap<String, T> = mutableMapOf()

    fun get(key: String): T = cacheMap.getValue(key)

    fun put(key: String, obj: T): T = cacheMap.put(key, obj).run { obj }
}