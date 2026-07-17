package org.github.mbarberot.mtg.grimoire.cards.storage.api

import org.github.mbarberot.mtg.grimoire.cards.domain.Card

interface CardStore {
    fun searchCards(query: String, page: Int = 1, size: Int = 10): Collection<Card>
    fun countCards(query: String): Int
    fun getCardById(id: String): Card?
    fun addCard(card: Card)
    fun removeAll(): Any?
    fun countAll(): Int
}