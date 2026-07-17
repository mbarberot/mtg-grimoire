package org.github.mbarberot.mtg.grimoire.cards.domain.search

import org.github.mbarberot.mtg.grimoire.cards.domain.Card
import org.github.mbarberot.mtg.grimoire.cards.storage.api.CardStore

class CardSearch(val cardStore: CardStore) {
    fun search(query: String, page: Int = 1, size: Int = 10): SearchResult<Card> {
        val resultsCount = cardStore.countCards(query)
        return SearchResult(
                cardStore.searchCards(query, page, size),
                SearchMetadata(resultsCount, size, page, query)
        )
    }
}

