package org.github.mbarberot.mtg.grimoire.business.searches

import org.github.mbarberot.mtg.grimoire.components.cards.CardStore

class CardSearch(val cardStore: CardStore) {
    fun search(query: String, page: Int = 1, size: Int = 10): SearchResult {
        val resultsCount = cardStore.countCards(query)
        return SearchResult(
                cardStore.searchCards(query, page, size),
                SearchMetadata(resultsCount, size, page, query)
        )
    }
}

