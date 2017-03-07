package org.github.mbarberot.mtg.grimoire.services

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import org.github.mbarberot.mtg.grimoire.stores.CardStore
import org.github.mbarberot.mtg.grimoire.stores.VersionStore
import org.github.mbarberot.mtg.grimoire.migration.mtgjson.MTGApi
import org.github.mbarberot.mtg.grimoire.services.impl.DatabaseService
import org.github.mbarberot.mtg.grimoire.services.impl.JsonEngineService
import org.github.mbarberot.mtg.grimoire.services.impl.SparkService

class Services {
    val services = listOf(
            SparkService(),
            DatabaseService(),
            JsonEngineService()
    )

    fun initialize(): Kodein {
        services.forEach { it.init() }

        return Kodein {
            services.forEach { import(it.registerModule()) }

            bind<MTGApi>() with provider { MTGApi() }
            bind<CardStore>() with provider { CardStore(instance()) }
            bind<VersionStore>() with provider { VersionStore(instance()) }
        }
    }
}