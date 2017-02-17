package org.github.mbarberot.mtg.grimoire.components.migration

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.conf.global
import com.github.salomonbrys.kodein.instance
import org.github.mbarberot.mtg.grimoire.components.migration.mtgjson.MTGMigration

class MigrationRunner(val versionStore: VersionStore = Kodein.global.instance()) {
    fun runMigrations() {
        MTGMigration(versionStore.getVersion().mtgVersion).run()
    }
}