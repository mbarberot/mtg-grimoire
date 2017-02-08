package org.github.mbarberot.mtg.grimoire

import org.github.mbarberot.mtg.grimoire.api.Component
import org.github.mbarberot.mtg.grimoire.components.cards.CardComponent
import org.github.mbarberot.mtg.grimoire.components.database.DatabaseComponent
import org.github.mbarberot.mtg.grimoire.components.index.IndexComponent
import org.github.mbarberot.mtg.grimoire.components.jade.JadeComponent
import org.github.mbarberot.mtg.grimoire.components.migration.MigrationComponent
import org.github.mbarberot.mtg.grimoire.components.spark.SparkComponent

class App {
    private val components : List<Component> = listOf(
            SparkComponent(),
            DatabaseComponent(),
            JadeComponent(),
            IndexComponent(),
            CardComponent(),
            MigrationComponent()
    )

    fun start() {
        components.forEach { it.initialize() }
    }
}

