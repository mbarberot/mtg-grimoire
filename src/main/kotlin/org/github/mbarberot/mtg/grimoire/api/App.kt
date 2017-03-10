package org.github.mbarberot.mtg.grimoire.api

import com.github.salomonbrys.kodein.instance
import org.github.mbarberot.mtg.grimoire.api.migration.MigrationRunner
import org.github.mbarberot.mtg.grimoire.api.services.Services

object App {
    @JvmStatic fun main(args: Array<String>) {
        val services = Services().initialize()

        Router(services.instance(), services).initialize()

        Thread(MigrationRunner(services.instance(), services)).run()
    }
}

