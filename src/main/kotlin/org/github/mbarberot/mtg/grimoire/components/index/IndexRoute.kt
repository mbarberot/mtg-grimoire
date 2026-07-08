package org.github.mbarberot.mtg.grimoire.components.index

import io.javalin.http.Context

class IndexRoute(val indexView: IndexView) {
    fun handle(ctx: Context) {
        ctx.html(indexView.render())
    }
}