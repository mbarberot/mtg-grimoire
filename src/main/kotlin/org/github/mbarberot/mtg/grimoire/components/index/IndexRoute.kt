package org.github.mbarberot.mtg.grimoire.components.index

import io.javalin.http.Context

class IndexRoute(val indewView: IndexView) {
    fun handle(ctx: Context): String {
        return indewView.render()
    }
}