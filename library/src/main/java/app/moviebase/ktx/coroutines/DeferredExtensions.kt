package app.moviebase.ktx.coroutines

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.withTimeout

suspend fun <T> Deferred<T?>.awaitOrNull(): T? = try {
    await()
} catch (t: Throwable) {
    null
}

suspend fun <T> Deferred<T?>.awaitWithTimeout(timeMillis: Long): T? = try {
    withTimeout(timeMillis) {
        await()
    }
} catch (t: Throwable) {
    null
}
