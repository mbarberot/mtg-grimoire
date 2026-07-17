package org.github.mbarberot.mtg.grimoire.cards.server

import com.github.jknack.handlebars.Handlebars
import org.github.mbarberot.mtg.grimoire.cards.domain.Card

class CardView(private val handlebars: Handlebars) {
    fun render(card: Card): String {
        return handlebars.compile("components/card").apply(card)
    }
}