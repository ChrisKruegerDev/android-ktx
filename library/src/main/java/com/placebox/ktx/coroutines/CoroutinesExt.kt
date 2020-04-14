package com.placebox.ktx.coroutines

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

private const val INITIAL_DELAY = 500L
private const val MAX_DELAY = 1000L
private const val FACTOR = 2
private const val DEFAULT_TIMES = 3

suspend fun <T> scopeAsync(
    context: CoroutineContext = EmptyCoroutineContext,
    block: suspend CoroutineScope.() -> T
): Deferred<T> = coroutineScope { async(context, block = block) }

suspend fun scopeLaunch(
    block: suspend CoroutineScope.() -> Unit
) = coroutineScope { launch(block = block) }

fun <T> T.asDeferred() = CompletableDeferred(this)

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
