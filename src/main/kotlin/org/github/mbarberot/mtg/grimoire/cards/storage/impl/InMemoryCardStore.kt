package org.github.mbarberot.mtg.grimoire.cards.storage.impl

import org.github.mbarberot.mtg.grimoire.cards.storage.api.CardStore
import org.github.mbarberot.mtg.grimoire.cards.domain.Card


class InMemoryCardStore(initialCards: List<Card> = listOf()): CardStore {

    private val cards = mutableMapOf<String, Card>()

    init {
        initialCards.forEach { card -> addCard(card) }
    }

    override fun searchCards(query: String, page: Int, size: Int): Collection<Card> {
        val offset = (page - 1) * size
        return cards.values
            .filter { it.name.matches(Regex(".*$query.*", RegexOption.IGNORE_CASE)) }
            .drop(offset)
            .take(size)
            .toList()
    }

    override fun countCards(query: String): Int {
        return cards.values
            .count { it.name.matches(Regex(".*$query.*", RegexOption.IGNORE_CASE)) }
    }

    override fun getCardById(id: String): Card? = cards[id]

    override fun addCard(card: Card) {
        cards[card.multiverseId] = card
    }

    override fun removeAll() = cards.clear()

    override fun countAll(): Int = cards.size

}