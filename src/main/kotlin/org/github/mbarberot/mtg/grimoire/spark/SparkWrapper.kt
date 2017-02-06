package org.github.mbarberot.mtg.grimoire.spark

import org.github.mbarberot.mtg.grimoire.controller.Controller
import spark.Spark.get

class SparkWrapper(val controller: Controller) {

    fun declareRoutes() {
        val indexController = controller.getIndexController()
        get("/", { req, res -> indexController.getIndex() })
    }
}