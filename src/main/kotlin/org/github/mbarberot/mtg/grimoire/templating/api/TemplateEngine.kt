package org.github.mbarberot.mtg.grimoire.templating.api

interface TemplateEngine {
    fun render(location: String, data: Any)
}