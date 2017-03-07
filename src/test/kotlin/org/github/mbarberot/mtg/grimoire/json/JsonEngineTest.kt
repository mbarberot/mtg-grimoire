package org.github.mbarberot.mtg.grimoire.json

import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Assert.*
import org.junit.Test

class JsonEngineTest {
    @Test
    fun testToJson_delegateToJacksonMapper() {
        val mapper = mock<ObjectMapper> {
            on { writeValueAsString("foo") } doReturn "foo"
        }

        assertEquals("foo" , JsonEngine(mapper).toJson("foo"))

        verify(mapper, times(1)).writeValueAsString("foo")
    }
}