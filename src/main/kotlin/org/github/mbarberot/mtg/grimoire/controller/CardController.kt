package org.github.mbarberot.mtg.grimoire.controller

import org.github.mbarberot.mtg.grimoire.model.managers.CardManager
import org.github.mbarberot.mtg.grimoire.view.View

class CardController(val cardManager: CardManager) : AbstractController() {
    fun getCard(id: String, view: View): Any = render(view, "parts/card", "card", cardManager.getCardById(id))
    fun getCards(query: String, view: View): Any = render(view, "parts/search-results", "cards", cardManager.searchCards(query))
}

