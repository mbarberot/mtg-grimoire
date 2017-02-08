package org.github.mbarberot.mtg.grimoire

import org.github.mbarberot.mtg.grimoire.business.updates.Updater
import org.github.mbarberot.mtg.grimoire.components.cards.CardComponent
import org.github.mbarberot.mtg.grimoire.components.database.DatabaseComponent
import org.github.mbarberot.mtg.grimoire.components.index.IndexComponent
import org.github.mbarberot.mtg.grimoire.components.jade.JadeComponent
import org.github.mbarberot.mtg.grimoire.components.spark.SparkComponent

class App {

    fun start() {
        initComponents()
        update()
    }

    private fun initComponents() {
        listOf(
                SparkComponent(),
                DatabaseComponent(),
                JadeComponent(),
                IndexComponent(),
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

