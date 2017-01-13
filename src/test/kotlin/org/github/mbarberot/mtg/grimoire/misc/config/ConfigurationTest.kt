package org.github.mbarberot.mtg.grimoire.misc.config

import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ConfigurationTest {

    lateinit var configuration: Configuration

    @Mock lateinit var environment: Environment

    @Before
    fun setUp() {
        configuration = Configuration(environment)
    }

    @Test
    fun getDefaultPort() {
        doReturn(null).whenever(environment).get("PORT")
        assertEquals(8080, configuration.getServerPort())
    }

    @Test
    fun getPortProvidedByEnvironment() {
        doReturn("1111").whenever(environment).get("PORT")
        assertEquals(1111, configuration.getServerPort())
    }

    @Test
    fun getDatabaseNameByEnvironment() {
        doReturn("foo").whenever(environment).get("DB_NAME")
        assertEquals("foo", configuration.getDatabaseName())
    }

    @Test
    fun getDatabaseURLByEnvironment() {
        doReturn("foo").whenever(environment).get("DB_URL")
        assertEquals("foo", configuration.getDatabaseURL())
    }
}
