package org.github.mbarberot.mtg.grimoire.apps.desktop.db

import org.github.mbarberot.mtg.grimoire.core.models.Version
import org.github.mbarberot.mtg.grimoire.core.stores.VersionStore


class H2VersionStore : VersionStore {
    override fun getVersion(): Version = Version(null, "0.0.0", "0.0.0")

    override fun createVersion(): Version = Version(null, "0.0.0", "0.0.0")

    override fun updateVersion(version: Version) {
    }
} 