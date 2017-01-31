package org.github.mbarberot.mtg.grimoire.business.searches

import org.github.mbarberot.mtg.grimoire.model.managers.CardManager

class CardSearch(val cardManager: CardManager) {
    fun search(query: String, page: Long = 1, size: Int = 10): SearchResult {
        return SearchResult(
                cardManager.searchCards(query, page, size), 
                SearchMetadata(cardManager.countCards(query), size, page, query))
    }
}

