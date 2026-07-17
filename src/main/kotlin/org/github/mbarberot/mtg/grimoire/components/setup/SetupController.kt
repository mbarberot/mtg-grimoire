package org.github.mbarberot.mtg.grimoire.components.setup

import com.github.jknack.handlebars.Handlebars
import io.javalin.http.Context

class SetupController(private val setupView: SetupView) {

    fun handle(ctx: Context) {
        ctx.html(setupView.render())
    }

}

class SetupView(private val handlebars: Handlebars) {

    fun render(): String = handlebars.compile("pages/setup").apply(null);

}
