package org.github.mbarberot.mtg.grimoire

import com.github.salomonbrys.kodein.instance
import org.github.mbarberot.mtg.grimoire.migration.MigrationRunner
import org.github.mbarberot.mtg.grimoire.services.Services

object App {
    @JvmStatic fun main(args: Array<String>) {
        val services = Services().initialize()

        Router(services.instance(), services).initialize()

        Thread(MigrationRunner(services.instance(), services)).run()
    }
}

