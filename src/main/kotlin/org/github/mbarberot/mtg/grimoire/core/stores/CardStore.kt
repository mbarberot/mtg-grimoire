package org.github.mbarberot.mtg.grimoire.core.stores

import org.github.mbarberot.mtg.grimoire.core.models.Card

interface CardStore {
    fun searchCards(query: String, page: Long, size: Int): Collection<Card>
    fun countCards(query: String): Long
    fun getCardById(id: String): Card
    fun addCard(card: Card)
    fun removeAll()
}