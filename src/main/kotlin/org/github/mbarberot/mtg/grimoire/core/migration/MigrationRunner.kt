package org.github.mbarberot.mtg.grimoire.core.migration

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import org.github.mbarberot.mtg.grimoire.core.migration.mtgjson.CardUpdater
import org.github.mbarberot.mtg.grimoire.core.migration.mtgjson.MTGApi
import org.github.mbarberot.mtg.grimoire.core.migration.mtgjson.MTGMigration
import org.github.mbarberot.mtg.grimoire.core.stores.VersionStore
import java.util.logging.Logger

class MigrationRunner(val services: Kodein) : Runnable {
    companion object {
        val LOG: Logger = Logger.getLogger(MigrationRunner::class.java.name)
    }

    val versionStore : VersionStore = services.instance()

    override fun run() {
        var version = versionStore.getVersion()

        version = MTGMigration(version, MTGApi(), CardUpdater(services.instance())).run()

        LOG.info { "Updating version : $version" }
        versionStore.updateVersion(version)
    }
}