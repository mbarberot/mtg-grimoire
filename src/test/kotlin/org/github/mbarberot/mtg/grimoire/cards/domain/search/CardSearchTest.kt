package org.github.mbarberot.mtg.grimoire.cards.domain.search


import org.github.mbarberot.mtg.grimoire.cards.domain.Card
import org.github.mbarberot.mtg.grimoire.cards.domain.search.CardSearch
import org.github.mbarberot.mtg.grimoire.cards.domain.search.SearchMetadata
import org.github.mbarberot.mtg.grimoire.cards.storage.impl.InMemoryCardStore
import kotlin.test.Test
import kotlin.test.assertEquals

class CardSearchTest {

    @Test
    fun testSearch() {
        val cards = listOfCards(15)

        val model = InMemoryCardStore(cards)

        val (results, metadata) = CardSearch(model).search("4")

        assertEquals(2, results.size)
        assertEquals(listOf(cards[3], cards[13]), results)
        assertEquals(SearchMetadata(2, 10, 1, "4"), metadata)
    }

    fun listOfCards(length: Int): List<Card> {
        return buildList {
            for (i in 1..length) {
                add(Card(
                    multiverseId = "id-$i",
                    name = "Card $i",
                    set = "Test Set",
                    type = "Test",
                ))
            }
        }
    }
}

