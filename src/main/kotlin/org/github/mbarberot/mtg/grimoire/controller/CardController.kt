package org.github.mbarberot.mtg.grimoire.controller

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.conf.global
import com.github.salomonbrys.kodein.instance
import org.github.mbarberot.mtg.grimoire.business.searches.CardSearch
import org.github.mbarberot.mtg.grimoire.components.cards.CardStore
import org.github.mbarberot.mtg.grimoire.view.View
import org.github.mbarberot.mtg.grimoire.view.jade.CardsView

class CardController(val cardStore: CardStore) {
    fun getCards(query: String, page: Long): Any {
        return CardsView(Kodein.global.instance()).render(CardSearch(cardStore).search(query, page))
    }
}

