package com.moviebase.ktx.coroutines

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

const val INITIAL_DELAY = 500L
const val MAX_DELAY = 1000L
const val FACTOR = 2
const val DEFAULT_TIMES = 3

suspend fun <T> scopeAsync(
    context: CoroutineContext = EmptyCoroutineContext,
    block: suspend CoroutineScope.() -> T
): Deferred<T> = coroutineScope { async(context, block = block) }

suspend fun scopeLaunch(
    block: suspend CoroutineScope.() -> Unit
) = coroutineScope { launch(block = block) }

suspend fun <T> Deferred<T?>.awaitOrNull(): T? = try {
    await()
} catch (t: Throwable) {
    null
}

suspend fun <T> awaitRetry(
    times: Int = DEFAULT_TIMES,
    initialDelay: Long = INITIAL_DELAY,
    factor: Int = FACTOR,
    maxDelay: Long = MAX_DELAY,
    retryWhen: suspend (Throwable) -> Boolean,
    block: suspend () -> T
): T {
    var currentDelay = initialDelay
    repeat(times - 1) {
        try {
            return block()
        } catch (t: Throwable) {
            if (!retryWhen(t))
                throw t
        }

        delay(currentDelay)
        currentDelay = (currentDelay * factor).coerceAtMost(maxDelay)
    }

    // last attempt to execute block
    return block()
}

fun exceptionHandler(block: (Throwable) -> Unit) = CoroutineExceptionHandler { _, throwable ->
    block(throwable)
}
