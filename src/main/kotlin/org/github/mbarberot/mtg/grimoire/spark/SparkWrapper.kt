package org.github.mbarberot.mtg.grimoire.spark

import org.github.mbarberot.mtg.grimoire.controller.Controller
import org.github.mbarberot.mtg.grimoire.view.View
import spark.Spark.get

class SparkWrapper(val controller: Controller) {

    fun declareRoutes() {
        val cardController = controller.getCardController()
        get("/api/cards/:id", { req, res -> cardController.getCard(req.params(":id"), View()) })
        get("/api/cards", { req, res ->
            cardController.getCards(
                    req.queryParams("q"),
                    req.queryParams("page")?.toLong() ?: 1,
                    View()
            )
        })

        val indexController = controller.getIndexController()
        get("/", { req, res -> indexController.getIndex(View()) })
    }
}