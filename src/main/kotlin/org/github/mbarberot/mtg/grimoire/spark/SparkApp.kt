package org.github.mbarberot.mtg.grimoire.spark

import org.github.mbarberot.mtg.grimoire.controller.Controller
import org.github.mbarberot.mtg.grimoire.misc.config.Configuration
import org.github.mbarberot.mtg.grimoire.view.View
import spark.Spark.*

class SparkApp(configuration: Configuration, val controller: Controller) {

    init {
        staticFiles.location("/public")
        port(configuration.getServerPort())
    }

    fun declareRoutes() {
        val cardController = controller.getCardController()
        get("/api/cards/:id", { req, res -> cardController.getCard(req.params(":id"), View()) })
        get("/api/cards", { req, res -> cardController.getCards(req.queryParams("q"), View()) })

        val indexController = controller.getIndexController()
        get("/", { req, res -> indexController.getIndex(View()) })
    }
}