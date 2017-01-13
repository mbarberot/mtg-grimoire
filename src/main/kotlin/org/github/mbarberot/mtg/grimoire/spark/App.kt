package org.github.mbarberot.mtg.grimoire.spark

import org.apache.commons.logging.LogFactory
import org.github.mbarberot.mtg.grimoire.misc.config.Configuration
import org.github.mbarberot.mtg.grimoire.model.Model
import spark.ModelAndView
import spark.Spark.*
import spark.template.jade.JadeTemplateEngine
import java.lang.Thread.sleep
import java.util.*

object App {

    @JvmStatic fun main(args: Array<String>) {
        val config = Configuration()

        port(config.getServerPort())
        staticFiles.location("/public")

        val templateEngine = initTemplateEngine()

        val model = Model(config)
        val cardManager = model.getCardManager()

        val dbManager = model.dbManager()
        dbManager.update()

        while (!dbManager.isReady) {
            sleep(100)
        }

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

