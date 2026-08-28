package org.github.mbarberot.mtg.grimoire.mtgapi.update

import com.github.zafarkhaja.semver.Version.parse
import org.github.mbarberot.mtg.grimoire.app.version.domain.Upgrader
import org.github.mbarberot.mtg.grimoire.app.version.domain.Version
import org.github.mbarberot.mtg.grimoire.cards.domain.CardUpdater
import org.github.mbarberot.mtg.grimoire.mtgapi.api.MTGApi
import java.util.logging.Logger

class MTGUpdater(
    val api: MTGApi,
    val cardUpdater: CardUpdater,
): Upgrader {

    companion object {
        val LOG = Logger.getLogger(MTGUpdater::class.java.name)
    }

    override fun upgrade(startVersion: Version): Version {
        val actualMtgVersion = parse(startVersion.mtgVersion)
        val lastMtgVersion = parse(api.getVersion())
        
        LOG.info { "Actual version : $actualMtgVersion vs Last version : $lastMtgVersion" }
        if (actualMtgVersion.isLowerThan(lastMtgVersion)) {
            val cards = api.getCards("LRW")
            cardUpdater.updateCards(cards)
        }
        return Version(
            startVersion.dbVersion,
            lastMtgVersion.toString()
        )
    }

}