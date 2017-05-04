package org.github.mbarberot.mtg.grimoire.core

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import org.github.mbarberot.mtg.grimoire.core.migration.MigrationRunner
import org.github.mbarberot.mtg.grimoire.core.migration.mtgjson.MTGApi
import org.github.mbarberot.mtg.grimoire.core.resources.CardResource


class Grimoire {

    val coreModule = Kodein.Module {
        bind<MTGApi>() with provider { MTGApi() }
        bind<CardResource>() with provider { CardResource(instance()) }
    }
    
    fun launch(): Grimoire {
        Thread(MigrationRunner()).run()
        return this
    }
}