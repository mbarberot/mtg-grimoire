package org.github.mbarberot.mtg.grimoire.core

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import org.github.mbarberot.mtg.grimoire.core.migration.MigrationRunner
import org.github.mbarberot.mtg.grimoire.core.migration.mtgjson.MTGApi
import org.github.mbarberot.mtg.grimoire.core.resources.CardResource


class Grimoire {
    private val inject: Kodein = Kodein {
        bind<MTGApi>() with provider { MTGApi() }
    }

    fun registerModule(module: Kodein.Module): Grimoire {
        with(inject) {
            registerModule(module)
        }
        return this
    }

    fun launch(): Grimoire {
        Thread(MigrationRunner(inject)).run()
        return this
    }

    fun getCardResource(): CardResource = CardResource(inject.instance())
}