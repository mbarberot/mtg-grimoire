package org.github.mbarberot.mtg.grimoire.components.cards

import com.github.jknack.handlebars.Handlebars

class CardView(private val handlebars: Handlebars) {
    fun render(card: Card): String {
        return handlebars.compile("components/card").apply(card)
    }
}