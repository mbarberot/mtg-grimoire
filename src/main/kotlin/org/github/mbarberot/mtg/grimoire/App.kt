package org.github.mbarberot.mtg.grimoire

import org.github.mbarberot.mtg.grimoire.business.updates.Updater
import org.github.mbarberot.mtg.grimoire.misc.config.Configuration
import org.github.mbarberot.mtg.grimoire.model.Model
import org.github.mbarberot.mtg.grimoire.spark.SparkApp

class App(val configuration: Configuration) {

    val model: Model = Model(configuration)

    fun start() {
        update()
        SparkApp(configuration, model).declareRoutes()
    }

    private fun update() {
        val updater = Updater(model)
        if (updater.needUpdate()) {
            updater.update()
        }
    }
}