package org.github.mbarberot.mtg.grimoire.apps.rest.db

import org.github.mbarberot.mtg.grimoire.core.models.Version
import org.github.mbarberot.mtg.grimoire.core.stores.VersionStore
import org.jongo.JongoNative


class MongoVersionStore(val jongo: JongoNative) : VersionStore {
    override fun getVersion() = getCollection().find().first() ?: createVersion()

    override fun createVersion(): Version {
        val version = Version(null, "1.0.0", "0.0.0")
        getCollection().insertOne(version)
        return version
    }

    override fun updateVersion(version: Version) {
        getCollection().replaceOne(
                jongo.query("{ _id: # }", version._id),
                version
        )
    }

    private fun getCollection() = jongo.getCollection("version", Version::class.java)
}

