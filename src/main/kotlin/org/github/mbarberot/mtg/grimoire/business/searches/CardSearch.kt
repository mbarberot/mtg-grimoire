package org.github.mbarberot.mtg.grimoire.business.searches

import org.github.mbarberot.mtg.grimoire.components.cards.CardStore

class CardSearch(val cardStore: CardStore) {
    fun search(query: String, page: Long = 1, size: Int = 10): SearchResult {
        return SearchResult(
                cardStore.searchCards(query, page, size),
                SearchMetadata(cardStore.countCards(query), size, page, query))
    }
}

