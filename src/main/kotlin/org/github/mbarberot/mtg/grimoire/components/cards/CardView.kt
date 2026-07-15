package org.github.mbarberot.mtg.grimoire.components.cards

import com.github.jknack.handlebars.Handlebars
import com.github.jknack.handlebars.TypeSafeTemplate
import org.github.mbarberot.mtg.grimoire.compileTypesafe

class CardView(private val handlebars: Handlebars) {
    fun render(card: Card): String {
        return handlebars
            .compileTypesafe(CardTemplate.LOCATION, CardTemplate::class.java)
            .apply(card)
    }
}

interface CardTemplate: TypeSafeTemplate<Card> {
    companion object {
        const val LOCATION = "components/card"
    }
}