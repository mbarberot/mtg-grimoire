package org.github.mbarberot.mtg.grimoire.components.migration

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
