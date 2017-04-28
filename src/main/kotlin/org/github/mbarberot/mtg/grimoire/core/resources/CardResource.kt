package org.github.mbarberot.mtg.grimoire.core.resources

import org.github.mbarberot.mtg.grimoire.core.stores.CardStore

class CardResource(private val cardStore: CardStore) {
    fun getCard(cardId: String) = cardStore.getCardById(cardId)
    fun getCards(query: String, page: Long = 1, size: Int = 10): Map<String, Any> {
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