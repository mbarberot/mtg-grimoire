package org.github.mbarberot.mtg.grimoire.spark

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.apache.commons.logging.LogFactory
import org.github.mbarberot.mtg.grimoire.misc.config.Configuration
import org.github.mbarberot.mtg.grimoire.model.Model
import org.github.mbarberot.mtg.grimoire.model.beans.Card
import org.github.mbarberot.mtg.grimoire.model.json.MTGSet
import org.github.mbarberot.mtg.grimoire.model.managers.CardManager
import spark.ModelAndView
import spark.Spark.*
import spark.template.jade.JadeTemplateEngine
import java.net.URL
import java.util.*

object App {

    val LOG = LogFactory.getLog(App::class.java.name)

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
        val managerFactory = Model(config)

        val cardManager = managerFactory.getCardManager()

        loadCards(cardManager)

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

    private fun loadCards(cardManager: CardManager) {
        val mapper = ObjectMapper().registerKotlinModule()
        cardManager.removeAll()
        URL("http://mtgjson.com/json/AllSetsArray.json").openStream().use { stream ->
            mapper.readValue<List<MTGSet>>(stream).forEach { set ->
                set.cards
                        .filter({ card -> card.multiverseid != 0 })
                        .forEach { card ->
                            cardManager.addCard(Card(card.name, card.multiverseid.toString(), emptyList()))
                        }
            }
        }
    }

    private fun initTemplateEngine(): JadeTemplateEngine {
        val templateEngine = JadeTemplateEngine()
        templateEngine.configuration().isPrettyPrint = true
        return templateEngine
    }
}

