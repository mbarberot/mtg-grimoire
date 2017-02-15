package org.github.mbarberot.mtg.grimoire.components.migration.mtgjson

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.conf.global
import com.github.salomonbrys.kodein.instance
import org.github.mbarberot.mtg.grimoire.components.migration.mtgjson.CardUpdater
import org.github.mbarberot.mtg.grimoire.components.migration.mtgjson.MTGApi

class MTGMigration(
        val actualMtgVersion: String,
        val api: MTGApi = Kodein.global.instance(),
        val cardUpdater: CardUpdater = CardUpdater(Kodein.global.instance())
) {
    fun run() {
        cardUpdater.updateCards(api.getSets())
    }
}