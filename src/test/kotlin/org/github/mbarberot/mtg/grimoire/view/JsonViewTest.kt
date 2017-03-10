package org.github.mbarberot.mtg.grimoire.view

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import junit.framework.TestCase.assertEquals
import org.github.mbarberot.mtg.grimoire.api.json.JsonEngine
import org.github.mbarberot.mtg.grimoire.api.view.JsonView
import org.junit.Test
import spark.Response

class JsonViewTest {
    @Test
    fun testRender() {
        val response = mock<Response>()

        val mapper = mock<JsonEngine> {
            on { toJson("foo") } doReturn "{ foo }"
        }

        assertEquals("{ foo }", JsonView(response, mapper).render { "foo" })

        verify(response, times(1)).header("Content-Type", "application/json")
    }
}