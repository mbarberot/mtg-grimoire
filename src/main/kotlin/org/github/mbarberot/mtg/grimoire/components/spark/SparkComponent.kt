package org.github.mbarberot.mtg.grimoire.components.spark

import org.github.mbarberot.mtg.grimoire.api.Component
import spark.Spark

class SparkComponent : Component {
    private val DEFAULT_PORT = 8080

    override fun initialize() {
        Spark.staticFiles.location("/public")
        Spark.port(System.getenv("PORT")?.toInt() ?: DEFAULT_PORT)
    }

    override fun declareRoutes() {
    }
}