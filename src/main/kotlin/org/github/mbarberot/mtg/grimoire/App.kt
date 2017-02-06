package org.github.mbarberot.mtg.grimoire

import org.github.mbarberot.mtg.grimoire.business.updates.Updater
import org.github.mbarberot.mtg.grimoire.components.cards.CardComponent
import org.github.mbarberot.mtg.grimoire.components.database.DatabaseComponent
import org.github.mbarberot.mtg.grimoire.components.jade.JadeComponent
import org.github.mbarberot.mtg.grimoire.components.spark.SparkComponent
import org.github.mbarberot.mtg.grimoire.controller.Controller
import org.github.mbarberot.mtg.grimoire.spark.SparkWrapper

class App {

    val controller: Controller = Controller()

    fun start() {
        initComponents()
        update()
        SparkWrapper(controller)
                .declareRoutes()
    }

    private fun initComponents() {
        listOf(
                SparkComponent(),
                DatabaseComponent(),
                JadeComponent(),
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

