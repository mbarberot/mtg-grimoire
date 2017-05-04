package org.github.mbarberot.mtg.grimoire.app.db

import org.github.mbarberot.mtg.grimoire.core.models.Card
import org.github.mbarberot.mtg.grimoire.core.stores.CardStore


class H2CardStore : CardStore {

    override fun searchCards(query: String, page: Long, size: Int): Collection<Card> =
            listOf(Card("foo", "foo", null, "foo", null, null, null, "foo", emptyList()))

    override fun countCards(query: String): Long = 1

    override fun getCardById(id: String): Card =
            Card("foo", "foo", null, "foo", null, null, null, "foo", emptyList())
    
    
    override fun addCard(card: Card) {
    }

    override fun removeAll() {
    }
}