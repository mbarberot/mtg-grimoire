package org.github.mbarberot.mtg.grimoire.api.business.searches

import org.github.mbarberot.mtg.grimoire.api.stores.CardStore

class CardSearch(val cardStore: CardStore) {
    fun search(query: String, page: Long = 1, size: Int = 10): SearchResult {
        return SearchResult(
                cardStore.searchCards(query, page, size),
                SearchMetadata(cardStore.countCards(query), size, page, query))
    }
}
