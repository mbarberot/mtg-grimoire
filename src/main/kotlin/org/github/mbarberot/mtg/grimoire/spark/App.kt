package org.github.mbarberot.mtg.grimoire.spark

import org.github.mbarberot.mtg.grimoire.misc.config.Configuration
import org.github.mbarberot.mtg.grimoire.model.managers.CardManager
import spark.ModelAndView
import spark.Spark.*
import spark.template.jade.JadeTemplateEngine
import java.util.*

object App {

    @JvmStatic fun main(args: Array<String>) {
        val config = Configuration()

        configure(config)
        declareRoutes()
    }

    private fun configure(config: Configuration) {
        port(config.getServerPort())
        staticFiles.location("/public")
    }

    private fun declareRoutes() {
        val templateEngine = JadeTemplateEngine()
        val cardManager = CardManager()

        post("/ic/search", { req, res ->
            val query = req.queryParams("q")
            ModelAndView(
                    mapOf(Pair("cards", cardManager.getCards().filter { card -> card.name.toLowerCase().startsWith(query.toLowerCase()) })),
                    "parts/search-results"
            )
        }, templateEngine)

        get("/", { request, response -> ModelAndView(Collections.emptyMap<Any, Any>(), "pages/index") }, templateEngine)

    }
}

