package org.github.mbarberot.mtg.grimoire.components.cards

import com.github.salomonbrys.kodein.conf.global
import com.github.salomonbrys.kodein.instance
import org.github.mbarberot.mtg.grimoire.business.searches.CardSearch
import org.github.mbarberot.mtg.grimoire.components.cards.CardsView
import spark.Request
import spark.Response
import spark.Route

class GetCardsRoute(val cardStore: CardStore) : Route {
    override fun handle(request: Request, response: Response): Any {
        val query = request.queryParams("q")
        val page = request.queryParams("page")?.toLong() ?: 1

        return CardsView(com.github.salomonbrys.kodein.Kodein.global.instance())
                .render(CardSearch(cardStore).search(query, page))
    }
}