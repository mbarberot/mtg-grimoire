package org.github.mbarberot.mtg.grimoire.components.index

import com.github.jknack.handlebars.Handlebars
import org.github.mbarberot.mtg.grimoire.business.searches.SearchMetadata
import org.github.mbarberot.mtg.grimoire.components.cards.Card
import org.github.mbarberot.mtg.grimoire.components.cards.generatePagination

class IndexView(private val handlebars: Handlebars) {
    fun render(cards: Collection<Card> = listOf(), metadata: SearchMetadata): String {
        return handlebars
            .compile("pages/index")
            .apply(
                mapOf(
                    Pair("cards", cards),
                    Pair("pagination", generatePagination(metadata)),
                )
            )
    }
}