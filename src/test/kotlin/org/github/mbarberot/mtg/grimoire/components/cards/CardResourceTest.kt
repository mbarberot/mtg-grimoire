package org.github.mbarberot.mtg.grimoire.components.cards

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.github.mbarberot.mtg.grimoire.models.Card
import org.github.mbarberot.mtg.grimoire.resources.CardResource
import org.github.mbarberot.mtg.grimoire.stores.CardStore
import org.junit.Assert.assertEquals
import org.junit.Test

class CardResourceTest {
    @Test
    fun getCard() {
        val card = mock<Card>()

        val store = mock<CardStore> {
            on { getCardById("some-id") } doReturn card
        }

        assertEquals(card, CardResource(store).getCard("some-id"))
    }

    @Test
    fun getCards() {
        val card = mock<Card>()

        val store = mock<CardStore> {
            on { searchCards("some-id", 1, 10) } doReturn listOf(card)
        }

        assertEquals(
                listOf(card),
                CardResource(store).getCards("some-id", 1, 10)
        )
    }
}