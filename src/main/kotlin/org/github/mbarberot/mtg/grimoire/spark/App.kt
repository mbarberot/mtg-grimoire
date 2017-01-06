package org.github.mbarberot.mtg.grimoire.spark

import org.github.mbarberot.mtg.grimoire.misc.config.Configuration
import org.github.mbarberot.mtg.grimoire.model.ManagerFactory
import spark.ModelAndView
import spark.Spark.*
import spark.template.jade.JadeTemplateEngine
import java.util.*

object App {

    @JvmStatic fun main(args: Array<String>) {
        val config = Configuration()

        configure(config)
        declareRoutes(config)
    }

    private fun configure(config: Configuration) {
        port(config.getServerPort())
        staticFiles.location("/public")
    }

    private fun declareRoutes(config: Configuration) {
        val templateEngine = initTemplateEngine()
        val managerFactory = ManagerFactory(config)

        val cardManager = managerFactory.getCardManager()

        post("/ic/search", { req, res ->
            val query = req.queryParams("q")
            ModelAndView(
                    mapOf(Pair("cards", cardManager.searchCards(query))),
                    "parts/search-results"
            )
        }, templateEngine)

        post("/ic/card/:id", { req, res ->
            val id = req.params(":id")
            ModelAndView(
                    mapOf(Pair("card", cardManager.getCardById(id))),
                    "parts/card"
            )
        }, templateEngine)

        get("/", { request, response -> ModelAndView(Collections.emptyMap<Any, Any>(), "pages/index") }, templateEngine)
    }

    private fun initTemplateEngine(): JadeTemplateEngine {
        val templateEngine = JadeTemplateEngine()
        templateEngine.configuration().isPrettyPrint = true
        return templateEngine
    }
}

