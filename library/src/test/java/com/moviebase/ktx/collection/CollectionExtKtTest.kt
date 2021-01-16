package com.moviebase.ktx.collection

import org.junit.Assert
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Describing a CollectionExtKt")
class CollectionExtKtTest {

    @Nested
    inner class `when put value if absent in map` {

        val map = mutableMapOf<Int, String>()

        @Test
        fun `it sets new value if key is absent`() {
            map.computeWhenAbsent(1) { "value" }
            Assert.assertEquals("value", map[1])
        }

        @Test
        fun `it returns new value if key is absent`() {
            val value = map.computeWhenAbsent(1) { "value" }
            Assert.assertEquals("value", value)
        }

        @Test
        fun `it returns available value if key is not absent`() {
            map[1] = "oldValue"
            val value = map.computeWhenAbsent(1) { "value" }
            Assert.assertEquals("oldValue", value)
        }

    }



}
