package org.github.mbarberot.mtg.grimoire.migration.mtgjson

import com.nhaarman.mockito_kotlin.*
import org.github.mbarberot.mtg.grimoire.core.migration.mtgjson.CardUpdater
import org.github.mbarberot.mtg.grimoire.core.migration.mtgjson.MTGApi
import org.github.mbarberot.mtg.grimoire.core.migration.mtgjson.MTGMigration
import org.github.mbarberot.mtg.grimoire.core.migration.mtgjson.MTGSet
import org.github.mbarberot.mtg.grimoire.core.models.Version
import org.junit.Assert.assertEquals
import org.junit.Test

class MTGMigrationTest {

    @Test
    fun run_needUpdate() {
        val sets = emptyList<MTGSet>()
        val mtgApi = mock<MTGApi> {
            on { getVersion() } doReturn "4.5.6"
            on { getSets() } doReturn sets
        }
        val cardUpdater = mock<CardUpdater>()
        val version = mock<Version> {
            on { mtgVersion } doReturn "0.0.0"
            on { dbVersion } doReturn "1.0.0"
        }

        assertEquals(
                Version("1.0.0", "4.5.6"),
                MTGMigration(version, mtgApi, cardUpdater).run()
        )

        verify(cardUpdater, times(1)).updateCards(any())
    }

    @Test
    fun run_noUpdate() {
        val mtgApi = mock<MTGApi> {
            on { getVersion() } doReturn "4.5.6"
        }
        val cardUpdater = mock<CardUpdater>()
        val version = mock<Version> {
            on { mtgVersion } doReturn "4.5.6"
            on { dbVersion } doReturn "1.0.0"
        }

        assertEquals(
                Version("1.0.0", "4.5.6"),
                MTGMigration(version, mtgApi, cardUpdater).run()
        )

        verify(cardUpdater, times(0)).updateCards(any())
    }
}
