package org.github.mbarberot.mtg.grimoire.components.migration

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.conf.global
import com.github.salomonbrys.kodein.instance
import org.github.mbarberot.mtg.grimoire.components.migration.mtgjson.MTGMigration
import java.util.logging.Logger

class MigrationRunner(val versionStore: VersionStore = Kodein.global.instance()) {
    companion object {
        val LOG = Logger.getLogger(MigrationRunner::class.java.name)
    }
    
    fun runMigrations() {
        var version = versionStore.getVersion()
        
        version = MTGMigration(version).run()
        
        LOG.info { "Updating version : $version" }
        versionStore.updateVersion(version)
    }
}