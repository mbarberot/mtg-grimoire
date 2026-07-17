package org.github.mbarberot.mtg.grimoire.components.index

import com.github.jknack.handlebars.Handlebars
import org.github.mbarberot.mtg.grimoire.cards.domain.search.SearchMetadata
import org.github.mbarberot.mtg.grimoire.cards.domain.Card
import org.github.mbarberot.mtg.grimoire.cards.server.generatePagination

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