package org.github.mbarberot.mtg.grimoire.cards.server

import io.javalin.http.Context
import org.github.mbarberot.mtg.grimoire.cards.storage.api.CardStore


class GetCardRoute(
    val cardStore: CardStore,
    val cardView: CardView
)  {
    fun handle(ctx: Context) {
        val card = cardStore.getCardById(ctx.pathParam("id"))

        if(card == null) {
            ctx.status(404)
            ctx.html("Card not found")
            return
        }

        ctx.html(cardView.render(card))
    }
}