package org.github.mbarberot.mtg.grimoire.api.migration

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import org.github.mbarberot.mtg.grimoire.api.migration.mtgjson.CardUpdater
import org.github.mbarberot.mtg.grimoire.api.migration.mtgjson.MTGApi
import org.github.mbarberot.mtg.grimoire.api.migration.mtgjson.MTGMigration
import org.github.mbarberot.mtg.grimoire.api.stores.VersionStore
import java.util.logging.Logger

class MigrationRunner(val versionStore: VersionStore, val services: Kodein) : Runnable {
    companion object {
        val LOG: Logger = Logger.getLogger(MigrationRunner::class.java.name)
    }

    override fun run() {
        var version = versionStore.getVersion()

        version = MTGMigration(version, MTGApi(), CardUpdater(services.instance())).run()

        LOG.info { "Updating version : $version" }
        versionStore.updateVersion(version)
    }
}