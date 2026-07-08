package org.github.mbarberot.mtg.grimoire.components.migration

interface VersionStore {
    fun getVersion(): Version
    fun updateVersion(version: Version)
}