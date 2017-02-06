package org.github.mbarberot.mtg.grimoire.view

import de.neuland.jade4j.JadeConfiguration
import org.github.mbarberot.mtg.grimoire.business.searches.SearchResult
import org.github.mbarberot.mtg.grimoire.components.cards.Card
import org.github.mbarberot.mtg.grimoire.view.jade.CardView
import org.github.mbarberot.mtg.grimoire.view.jade.CardsView
import org.github.mbarberot.mtg.grimoire.view.jade.IndexView
import org.github.mbarberot.mtg.grimoire.view.jade.helpers.ManaHelper

class View(val jade: JadeConfiguration = JadeConfiguration()) {
    init {
        jade.templateLoader = GrimoireTemplateLoader("/templates/")
        jade.isPrettyPrint = true
        jade.sharedVariables = mapOf(Pair("mana", ManaHelper()))
    }

    fun cardsView(search: SearchResult): Any {
        return CardsView(jade).render(search)
    }

    fun cardView(card: Card): Any {
        return CardView(jade).render(card)
    }

    fun indexView(): Any {
        return IndexView(jade).render()
    }
}

