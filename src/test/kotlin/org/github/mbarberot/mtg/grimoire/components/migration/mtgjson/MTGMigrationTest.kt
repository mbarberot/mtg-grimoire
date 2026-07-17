package org.github.mbarberot.mtg.grimoire.components.migration.mtgjson

import org.github.mbarberot.mtg.grimoire.AppConfig
import org.github.mbarberot.mtg.grimoire.components.cards.InMemoryCardStore
import org.github.mbarberot.mtg.grimoire.components.migration.Version
import kotlin.test.Test
import kotlin.test.assertEquals

class TestMTGApi(
    private val version: String,
    private val sets: List<MTGSet>,
    private val cards: List<MTGCard>,
) : MTGApi {

    override fun getSets(): List<MTGSet> {
        return sets
    }

    override fun getCards(setCode: String): List<MTGCard> {
        return cards
    }

    override fun getVersion(): String {
        return version
    }


}

class MTGMigrationTest {

    @Test
    fun run_needUpdate() {
        val sets = listOf(makeMTGSet("Test Set"))
        val cards = makeMTGCards(10)
        val cardStore = InMemoryCardStore()
        val cardUpdater = CardUpdater(AppConfig(), cardStore, TagGenerator())

        val mtgApi = TestMTGApi("4.5.6", sets, cards)
        val version = Version("1.0.0", "0.0.0")

        val newVersion = MTGMigration(mtgApi, cardUpdater).run(version)

        assertEquals(Version("1.0.0", "4.5.6"), newVersion)
        assertEquals(10, cardStore.countAll());
    }

    @Test
    fun run_noUpdate() {
        val cards = makeMTGCards(10)
        val mtgApi = TestMTGApi("4.5.6", listOf(makeMTGSet("Test Set")), cards)
        val cardStore = InMemoryCardStore()
        val cardUpdater = CardUpdater(appConfig = AppConfig(language = "French"), cardStore, TagGenerator())
        val version = Version("1.0.0", "4.5.6")

        assertEquals(
            Version("1.0.0", "4.5.6"),
            MTGMigration(mtgApi, cardUpdater).run(version)
        )
        assertEquals(0, cardStore.countAll());
    }
}
