package org.github.mbarberot.mtg.grimoire.spark

import org.github.mbarberot.mtg.grimoire.misc.config.Configuration
import org.github.mbarberot.mtg.grimoire.model.Model
import spark.ModelAndView
import spark.Spark.*
import spark.template.jade.JadeTemplateEngine
import java.util.*

class SparkApp(configuration: Configuration, val model: Model) {

    val templateEngine = initTemplateEngine()

    init {
        staticFiles.location("/public")
        port(configuration.getServerPort())
    }

    private fun initTemplateEngine(): JadeTemplateEngine {
        val templateEngine = JadeTemplateEngine()
        templateEngine.configuration().isPrettyPrint = true
        return templateEngine
    }

    fun declareRoutes() {
        val cardManager = model.getCardManager()
        
        get("/api/cards/:id", { req, res ->
            val id = req.params(":id")
            ModelAndView(
                    mapOf(Pair("card", cardManager.getCardById(id))),
                    "parts/card"
            )
        }, templateEngine)
        
        get("/api/cards", { req, res ->
            val query = req.queryParams("q")
            ModelAndView(
                    mapOf(Pair("cards", cardManager.searchCards(query))),
                    "parts/search-results"
            )
        }, templateEngine)

        get("/", { request, response -> ModelAndView(Collections.emptyMap<Any, Any>(), "pages/index") }, templateEngine)
    }
}