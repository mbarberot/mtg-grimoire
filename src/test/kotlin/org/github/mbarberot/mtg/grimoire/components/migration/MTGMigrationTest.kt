package org.github.mbarberot.mtg.grimoire.components.migration

import com.nhaarman.mockito_kotlin.*
import org.github.mbarberot.mtg.grimoire.components.migration.mtgjson.CardUpdater
import org.github.mbarberot.mtg.grimoire.components.migration.mtgjson.MTGApi
import org.github.mbarberot.mtg.grimoire.components.migration.mtgjson.MTGMigration
import org.github.mbarberot.mtg.grimoire.components.migration.mtgjson.MTGSet
import org.junit.Test

class MTGMigrationTest {

    @Test
    fun run() {
        val sets = emptyList<MTGSet>()
        val mtgApi = mock<MTGApi> {
            on { getVersion() } doReturn "4.5.6"
            on { getSets() } doReturn sets
        }
        val cardUpdater = mock<CardUpdater>()

        MTGMigration("0.0.0", mtgApi, cardUpdater).run()

        verify(cardUpdater, times(1)).updateCards(any())
    }
}
