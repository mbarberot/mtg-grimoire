package org.github.mbarberot.mtg.grimoire.mtgapi.update

import factories.CardFactory
import org.github.mbarberot.mtg.grimoire.app.version.domain.Version
import org.github.mbarberot.mtg.grimoire.cards.domain.Card
import org.github.mbarberot.mtg.grimoire.cards.domain.CardUpdater
import org.github.mbarberot.mtg.grimoire.cards.domain.Set
import org.github.mbarberot.mtg.grimoire.cards.storage.impl.InMemoryCardStore
import org.github.mbarberot.mtg.grimoire.mtgapi.api.MTGApi
import org.github.mbarberot.mtg.grimoire.tags.domain.TagGenerator
import kotlin.test.Test
import kotlin.test.assertEquals

class MTGUpdaterTest {

    @Test
    fun run_needUpdate() {
        val sets = listOf(CardFactory.makeSet("Test Set"))
        val cards = CardFactory.makeCards(10)
        val cardStore = InMemoryCardStore()
        val cardUpdater = CardUpdater(cardStore, TagGenerator())

        val mtgApi = InMemoryMTGApi("4.5.6", sets, cards)
        val version = Version("1.0.0", "0.0.0")

        val newVersion = MTGUpdater(mtgApi, cardUpdater).upgrade(version)

        assertEquals(Version("1.0.0", "4.5.6"), newVersion)
        assertEquals(10, cardStore.countAll());
    }

    @Test
    fun run_noUpdate() {
        val cards = CardFactory.makeCards(10)
        val set = CardFactory.makeSet("Test Set")
        val mtgApi = InMemoryMTGApi("4.5.6", listOf(set), cards)
        val cardStore = InMemoryCardStore()
        val cardUpdater = CardUpdater(cardStore, TagGenerator())
        val version = Version("1.0.0", "4.5.6")

        assertEquals(
            Version("1.0.0", "4.5.6"),
            MTGUpdater(mtgApi, cardUpdater).upgrade(version)
        )
        assertEquals(0, cardStore.countAll());
    }
}

class InMemoryMTGApi(
    private val version: String,
    private val sets: List<Set>,
    private val cards: List<Card>,
) : MTGApi {

    override fun getSets(): List<Set> {
        return sets
    }

    override fun getCards(setCode: String): List<Card> {
        return cards
    }

    override fun getVersion(): String {
        return version
    }
}
