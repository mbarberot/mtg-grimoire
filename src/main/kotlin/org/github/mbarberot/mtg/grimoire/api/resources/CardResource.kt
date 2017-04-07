package org.github.mbarberot.mtg.grimoire.api.resources

import org.github.mbarberot.mtg.grimoire.api.stores.CardStore

class CardResource(private val cardStore: CardStore) {
    fun getCard(cardId: String) = cardStore.getCardById(cardId)
    fun getCards(query: String, page: Long, size: Int): Map<String, Any> {
        return mapOf(
                Pair("cards", cardStore.searchCards(query, page, size)),
                Pair("meta", mapOf(
                        Pair("total", cardStore.countCards(query)),
                        Pair("size", size),
                        Pair("current_page", page)
                ))
        )
    }
}