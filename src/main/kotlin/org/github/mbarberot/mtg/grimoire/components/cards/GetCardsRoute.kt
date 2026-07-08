package org.github.mbarberot.mtg.grimoire.components.cards

import io.javalin.http.Context
import org.github.mbarberot.mtg.grimoire.business.searches.CardSearch

class GetCardsRoute(
    val cardSearch: CardSearch,
    val cardsView: CardsView,
) {
    fun handle(ctx: Context) {
        val query = ctx.queryParam("q") ?: ""
        val page = ctx.queryParam("page")?.toInt() ?: 1
        val search = cardSearch.search(query, page)

        ctx.html(cardsView.render(search))
    }
}