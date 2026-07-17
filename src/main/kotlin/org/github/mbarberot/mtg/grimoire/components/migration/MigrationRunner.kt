package org.github.mbarberot.mtg.grimoire.components.migration

import org.github.mbarberot.mtg.grimoire.components.migration.mtgjson.MTGMigration
import org.github.mbarberot.mtg.grimoire.app.version.storage.api.VersionStore
import java.util.logging.Logger

class MigrationRunner(val versionStore: VersionStore, val migration: MTGMigration) : Runnable {
    companion object {
        val LOG = Logger.getLogger(MigrationRunner::class.java.name)
    }
    
    override fun run() {
        var version = versionStore.getVersion()
        
        version = migration.run(version)
        
        LOG.info { "Updating version : $version" }
        versionStore.updateVersion(version)
    }
}