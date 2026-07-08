package org.github.mbarberot.mtg.grimoire.components.cards

import io.javalin.http.Context


class GetCardRoute(
    val cardStore: CardStore,
    val cardView: CardView
)  {
    fun handle(ctx: Context) {
        val card = cardStore.getCardById(ctx.pathParam("id"))

        if(card == null) {
            ctx.status(404)
            ctx.result("Card not found")
            return
        }

        ctx.result(cardView.render(card))
    }
}