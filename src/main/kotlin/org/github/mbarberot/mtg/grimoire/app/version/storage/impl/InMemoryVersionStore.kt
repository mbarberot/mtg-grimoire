package org.github.mbarberot.mtg.grimoire.app.version.storage.impl

import org.github.mbarberot.mtg.grimoire.app.version.storage.api.VersionStore
import org.github.mbarberot.mtg.grimoire.app.version.domain.Version

class InMemoryVersionStore: VersionStore {

    private var version: Version = createVersion()

    override fun getVersion(): Version {
        return version
    }

    private fun createVersion(): Version {
        val version = Version("1.0.0", "0.0.0")
        return version
    }

    override fun updateVersion(version: Version) {
        this.version = version
    }
}
