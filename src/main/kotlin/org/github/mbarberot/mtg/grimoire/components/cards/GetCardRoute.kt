package org.github.mbarberot.mtg.grimoire.components.cards

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.conf.global
import com.github.salomonbrys.kodein.instance
import org.github.mbarberot.mtg.grimoire.view.jade.CardView
import spark.Request
import spark.Response
import spark.Route

class GetCardRoute(val cardStore: CardStore) : Route {
    override fun handle(request: Request, response: Response): String {
        return CardView(Kodein.global.instance())
                .render(cardStore.getCardById(request.params(":id")))
    }
}