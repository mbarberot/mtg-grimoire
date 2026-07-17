package org.github.mbarberot.mtg.grimoire.components.index

import io.javalin.http.Context
import org.github.mbarberot.mtg.grimoire.cards.domain.search.CardSearch

class IndexRoute(
    private val cardSearch: CardSearch,
    private val indexView: IndexView,
) {
    fun handle(ctx: Context) {
        val (cards, metadata) = cardSearch.search("")
        ctx.html(indexView.render(cards, metadata))
    }
}