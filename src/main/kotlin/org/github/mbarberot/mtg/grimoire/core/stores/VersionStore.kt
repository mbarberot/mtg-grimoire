package org.github.mbarberot.mtg.grimoire.core.stores

import org.github.mbarberot.mtg.grimoire.core.models.Version

interface VersionStore {
    fun getVersion(): Version
    fun createVersion(): Version
    fun updateVersion(version: Version)
}