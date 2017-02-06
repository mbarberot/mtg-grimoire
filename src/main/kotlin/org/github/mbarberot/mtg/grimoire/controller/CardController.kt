package org.github.mbarberot.mtg.grimoire.controller

import org.github.mbarberot.mtg.grimoire.business.searches.CardSearch
import org.github.mbarberot.mtg.grimoire.components.cards.CardStore
import org.github.mbarberot.mtg.grimoire.view.View

class CardController(val cardStore: CardStore) {
    fun getCard(id: String, view: View): Any {
        return view.cardView(cardStore.getCardById(id))
    }

    fun getCards(query: String, page: Long, view: View): Any {
        return view.cardsView(CardSearch(cardStore).search(query, page))
    }
}

