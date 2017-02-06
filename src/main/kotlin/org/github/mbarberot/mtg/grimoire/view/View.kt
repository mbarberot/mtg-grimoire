package org.github.mbarberot.mtg.grimoire.view

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.conf.global
import com.github.salomonbrys.kodein.instance
import org.github.mbarberot.mtg.grimoire.business.searches.SearchResult
import org.github.mbarberot.mtg.grimoire.components.cards.Card
import org.github.mbarberot.mtg.grimoire.view.jade.CardView
import org.github.mbarberot.mtg.grimoire.view.jade.CardsView
import org.github.mbarberot.mtg.grimoire.view.jade.IndexView

class View {

    fun cardsView(search: SearchResult): Any {
        return CardsView(Kodein.global.instance()).render(search)
    }

    fun cardView(card: Card): Any {
        return CardView(Kodein.global.instance()).render(card)
    }

    fun indexView(): Any {
        return IndexView(Kodein.global.instance()).render()
    }
}

