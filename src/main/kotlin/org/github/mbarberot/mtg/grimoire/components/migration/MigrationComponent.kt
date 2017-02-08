package org.github.mbarberot.mtg.grimoire.components.migration

import org.github.mbarberot.mtg.grimoire.api.Component

class MigrationComponent : Component {
    override fun initialize() {
        val updater = Updater()
        if (updater.needUpdate()) {
            updater.update()
        }
    }
}