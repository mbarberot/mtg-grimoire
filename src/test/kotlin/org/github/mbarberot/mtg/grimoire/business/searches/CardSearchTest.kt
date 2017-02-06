package org.github.mbarberot.mtg.grimoire.business.searches

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.github.mbarberot.mtg.grimoire.model.beans.Card
import org.github.mbarberot.mtg.grimoire.components.cards.CardStore
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class CardSearchTest {
    @Test
    fun testSearch() {
        val cards = listOfCards(15)

        val model = mock<CardStore> {
            on { searchCards("toto") } doReturn cards
            on { countCards("toto") } doReturn cards.size.toLong()
        }

        val (results, metadata) = CardSearch(model).search("toto")
        assertEquals(cards, results)
        assertEquals(SearchMetadata(cards.size.toLong(), 10, 1, "toto"), metadata)
    }

    fun listOfCards(length: Int): Collection<Card> {
        val cards = ArrayList<Card>()
        for (i in 1..length) {
            cards.add(mock<Card> {})
        }
        return cards
    }
}

