package org.github.mbarberot.mtg.grimoire.components.migration.mtgjson

import com.github.zafarkhaja.semver.Version.parse
import org.github.mbarberot.mtg.grimoire.components.migration.Version
import java.util.logging.Logger

class MTGMigration(
    val api: MTGApi,
    val cardUpdater: CardUpdater,
) {
    companion object {
        val LOG = Logger.getLogger(MTGMigration::class.java.name)
    }

    fun run(startVersion: Version): Version {
        val actualMtgVersion = parse(startVersion.mtgVersion)
        val lastMtgVersion = parse(api.getVersion())
        
        LOG.info { "Actual version : $actualMtgVersion /vs/ Last version : $lastMtgVersion" }
        if (actualMtgVersion.isLowerThan(lastMtgVersion)) {
            cardUpdater.updateCards(api.getSets())
        }

        return Version(
                startVersion.dbVersion,
                lastMtgVersion.toString()
        )
    }
}