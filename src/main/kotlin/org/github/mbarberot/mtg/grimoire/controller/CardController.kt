package org.github.mbarberot.mtg.grimoire.controller

import org.github.mbarberot.mtg.grimoire.business.searches.CardSearch
import org.github.mbarberot.mtg.grimoire.model.managers.CardManager
import org.github.mbarberot.mtg.grimoire.view.View

class CardController(val cardManager: CardManager) {
    fun getCard(id: String, view: View): Any {
        return view.cardView(cardManager.getCardById(id))
    }

    fun getCards(query: String, page: Long, view: View): Any {
        return view.cardsView(CardSearch(cardManager).search(query, page))
    }
}

