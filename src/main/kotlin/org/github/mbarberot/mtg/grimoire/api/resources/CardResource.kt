package org.github.mbarberot.mtg.grimoire.api.resources

import org.github.mbarberot.mtg.grimoire.api.stores.CardStore

class CardResource(private val cardStore: CardStore) {
    fun getCard(cardId: String) = cardStore.getCardById(cardId)
    fun getCards(query: String, page: Long, size: Int) = cardStore.searchCards(query, page, size)
}