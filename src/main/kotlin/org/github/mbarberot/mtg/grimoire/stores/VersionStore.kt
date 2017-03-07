package org.github.mbarberot.mtg.grimoire.stores

import org.github.mbarberot.mtg.grimoire.models.Version
import org.jongo.JongoNative


class VersionStore(val jongo: JongoNative) {
    fun getVersion() = getCollection().find().first() ?: createVersion()

    private fun createVersion(): Version {
        val version = Version(null, "1.0.0", "0.0.0")
        getCollection().insertOne(version)
        return version
    }

    fun updateVersion(version: Version) {
        getCollection().replaceOne(
                jongo.query("{ _id: # }", version._id),
                version
        )
    }

    private fun getCollection() = jongo.getCollection("version", Version::class.java)
}