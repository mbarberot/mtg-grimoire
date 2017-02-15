package org.github.mbarberot.mtg.grimoire.components.migration

import org.github.mbarberot.mtg.grimoire.components.migration.mtgjson.MTGMigration

class MigrationRunner(val versionStore: VersionStore) {
    fun runMigrations() {
        MTGMigration(versionStore.getVersion().mtgVersion).run()
    }
}