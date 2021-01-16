package com.moviebase.ktx.type

import org.junit.Assert
import org.junit.jupiter.api.*

@DisplayName("Describing a BooleanExtKt")
class BooleanExtKtTest {

    @Test
    fun `is true`() {
        Assert.assertTrue(true.isTrue())
    }

    @Test
    fun `is false`() {
        Assert.assertFalse(false.isTrue())
    }

    @Test
    fun `null is false`() {
        val b: Boolean? = null
        Assert.assertFalse(b.isTrue())
    }

}
