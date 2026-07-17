package org.github.mbarberot.mtg.grimoire.cards.server

import io.javalin.http.Context
import org.github.mbarberot.mtg.grimoire.cards.domain.search.CardSearch

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