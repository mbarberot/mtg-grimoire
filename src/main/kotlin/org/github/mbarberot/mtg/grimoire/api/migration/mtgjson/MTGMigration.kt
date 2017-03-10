package org.github.mbarberot.mtg.grimoire.api.migration.mtgjson

import com.github.zafarkhaja.semver.Version.valueOf
import org.github.mbarberot.mtg.grimoire.api.models.Version
import java.util.logging.Logger

class MTGMigration(
        val version: Version,
        val api: MTGApi,
        val cardUpdater: CardUpdater
) {
    companion object {
        val LOG = Logger.getLogger(MTGMigration::class.java.name)
    }

    fun run(): Version {
        val actualMtgVersion = valueOf(version.mtgVersion)
        val lastMtgVersion = valueOf(api.getVersion())

        LOG.info { "Actual version : $actualMtgVersion /vs/ Last version : $lastMtgVersion" }
        if (actualMtgVersion.lessThan(lastMtgVersion)) {
            cardUpdater.updateCards(api.getSets())
        }

        return Version(
                version._id,
                version.dbVersion,
                lastMtgVersion.toString()
        )
    }
}