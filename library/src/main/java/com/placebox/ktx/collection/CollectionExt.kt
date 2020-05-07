package com.placebox.ktx.collection

fun <T> T?.toSingletonList() = if(this == null) emptyList() else listOf(this)

fun <T> Collection<T>?.isNotNullOrEmpty(): Boolean = !isNullOrEmpty()

fun <T> Collection<T>.toArrayList() = ArrayList(this)

fun <E> MutableList<E>.setAll(elements: Collection<E>) {
    clear()
    addAll(elements)
}

inline fun <T> Iterable<T>.firstIndex(predicate: (T) -> Boolean): Int {
    for ((index, item) in this.withIndex()) {
        if (predicate(item)) return index
    }
    return 0
}

fun <K, V> Map<K, V>.switch(): Map<V, K> = entries.associateBy({ it.value }) { it.key }

inline fun <K, V> MutableMap<K, V>.computeWhenAbsent(key: K, mapping: (K) -> V): V {
    val value = this[key]

    if (value == null) {
        val newValue: V = mapping(key)
        this[key] = newValue
        return newValue
    }

    return value
}

inline fun <T> Iterable<T>.firstOrThrow(predicate: (T) -> Boolean, t: () -> Throwable): T {
    for (element in this) if (predicate(element)) return element
    throw t.invoke()
}
