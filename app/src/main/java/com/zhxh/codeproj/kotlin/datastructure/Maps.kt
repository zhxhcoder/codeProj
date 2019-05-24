package cnife.datastructure

import cnife.datastructure.utils.Failure
import cnife.datastructure.utils.Result

interface Map<K, out V> {
    interface Entry<out K, out V> {
        val key: K
        val value: V
    }

    val size: Int

    val isEmpty: Boolean
        get() = size == 0

    fun containsKey(key: K): Boolean

    fun containsValue(value: @UnsafeVariance V): Boolean

    operator fun get(key: K): Result<V>

    fun getOrDefault(key: K, defaultValue: @UnsafeVariance V): V =
        get(key).let { if (it === Failure) defaultValue else it.value }

    val entries: Set<Entry<K, V>>

    val keys: Set<K>

    val values: Collection<V>

    fun toMutableMap(): MutableMap<K, @UnsafeVariance V>
}

interface MutableMap<K, V> : Map<K, V> {
    interface MutableEntry<out K, V> : Map.Entry<K, V> {
        fun setValue(value: V): V
    }

    fun put(key: K, value: V): Result<V>

    fun putAll(map: Map<out K, V>)

    fun remove(key: K): Result<V>

    fun remove(key: K, value: V): Boolean

    operator fun plusAssign(entry: Pair<K, V>) {
        put(entry.first, entry.second)
    }

    operator fun plusAssign(map: Map<out K, V>) {
        putAll(map)
    }

    operator fun minusAssign(key: K) {
        remove(key)
    }

    operator fun minusAssign(entry: Pair<K, V>) {
        remove(entry.first, entry.second)
    }

    override val entries: MutableSet<MutableMap.MutableEntry<K, V>>

    override val keys: MutableSet<K>

    override val values: MutableCollection<V>

    fun clear()
}