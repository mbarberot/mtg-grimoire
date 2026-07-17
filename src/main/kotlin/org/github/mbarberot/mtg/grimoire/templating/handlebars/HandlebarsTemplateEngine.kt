package org.github.mbarberot.mtg.grimoire.templating.handlebars

import com.github.jknack.handlebars.Handlebars
import org.github.mbarberot.mtg.grimoire.templating.api.TemplateEngine

class HandlebarsTemplateEngine(private val handlebars: Handlebars) : TemplateEngine {
    override fun render(location: String, data: Any) {
        handlebars.compile(location).apply(data)
    }
}