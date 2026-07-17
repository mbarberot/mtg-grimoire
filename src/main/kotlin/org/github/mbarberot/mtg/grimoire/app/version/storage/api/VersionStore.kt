package org.github.mbarberot.mtg.grimoire.app.version.storage.api

import org.github.mbarberot.mtg.grimoire.app.version.domain.Version

interface VersionStore {
    fun getVersion(): Version
    fun updateVersion(version: Version)
}