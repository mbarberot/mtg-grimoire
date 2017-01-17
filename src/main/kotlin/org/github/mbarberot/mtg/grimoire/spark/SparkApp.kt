package org.github.mbarberot.mtg.grimoire.spark

import com.google.common.io.Resources.getResource
import de.neuland.jade4j.Jade4J.render
import de.neuland.jade4j.JadeConfiguration
import de.neuland.jade4j.template.ClasspathTemplateLoader
import de.neuland.jade4j.template.FileTemplateLoader
import org.github.mbarberot.mtg.grimoire.misc.config.Configuration
import org.github.mbarberot.mtg.grimoire.model.Model
import org.github.mbarberot.mtg.grimoire.view.GrimoireTemplateLoader
import spark.Spark.*

class SparkApp(configuration: Configuration, val model: Model) {

    init {
        staticFiles.location("/public")
        port(configuration.getServerPort())
    }

    fun declareRoutes() {
        val cardManager = model.getCardManager()
        val jade = JadeConfiguration()
        
        jade.templateLoader = GrimoireTemplateLoader("/templates/")
        jade.isPrettyPrint = true

        get("/api/cards/:id", { req, res ->
            jade.renderTemplate(
                    jade.getTemplate("parts/card"),
                    mapOf(Pair("card", cardManager.getCardById(req.params(":id"))))
            )
        })

        get("/api/cards", { req, res ->
            jade.renderTemplate(
                    jade.getTemplate("parts/search-results"),
                    mapOf(Pair("cards", cardManager.searchCards(req.queryParams("q"))))
            )
        })

        get("/", { req, res ->
            jade.renderTemplate(
                    jade.getTemplate("pages/index"),
                    emptyMap()
            )
        })
    }
}