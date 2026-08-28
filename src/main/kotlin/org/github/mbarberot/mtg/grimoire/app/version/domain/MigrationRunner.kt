package org.github.mbarberot.mtg.grimoire.app.version.domain

import org.github.mbarberot.mtg.grimoire.app.version.storage.api.VersionStore
import java.util.logging.Logger

class MigrationRunner(val versionStore: VersionStore, val upgrader: Upgrader) : Runnable {
    companion object {
        val LOG = Logger.getLogger(MigrationRunner::class.java.name)
    }
    
    override fun run() {
        var version = versionStore.getVersion()
        
        version = upgrader.upgrade(version)
        
        LOG.info { "Updating version : $version" }
        versionStore.updateVersion(version)
    }
}