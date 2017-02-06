package org.github.mbarberot.mtg.grimoire

import org.github.mbarberot.mtg.grimoire.business.updates.Updater
import org.github.mbarberot.mtg.grimoire.components.cards.CardComponent
import org.github.mbarberot.mtg.grimoire.components.database.DatabaseComponent
import org.github.mbarberot.mtg.grimoire.controller.Controller
import org.github.mbarberot.mtg.grimoire.misc.config.Configuration
import org.github.mbarberot.mtg.grimoire.model.Model
import org.github.mbarberot.mtg.grimoire.spark.SparkWrapper

class App(val configuration: Configuration) {

    val model: Model = Model(configuration)
    val controller: Controller = Controller()

    fun start() {
        initComponents()
        update()
        SparkWrapper(configuration, controller)
                .declareRoutes()
    }

    private fun initComponents() {
        listOf(
                DatabaseComponent(),
                CardComponent()
        ).forEach { it.initialize() }
    }

    private fun update() {
        val updater = Updater()
        if (updater.needUpdate()) {
            updater.update()
        }
    }
}

