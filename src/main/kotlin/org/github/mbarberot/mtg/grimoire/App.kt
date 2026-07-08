package org.github.mbarberot.mtg.grimoire

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import de.neuland.jade4j.JadeConfiguration
import org.github.mbarberot.mtg.grimoire.business.searches.CardSearch
import org.github.mbarberot.mtg.grimoire.components.cards.*
import org.github.mbarberot.mtg.grimoire.components.index.IndexRoute
import org.github.mbarberot.mtg.grimoire.components.index.IndexView
import org.github.mbarberot.mtg.grimoire.components.jade.GrimoireTemplateLoader
import org.github.mbarberot.mtg.grimoire.components.jade.helpers.ManaHelper
import org.github.mbarberot.mtg.grimoire.components.migration.InMemoryVersionStore
import org.github.mbarberot.mtg.grimoire.components.migration.MigrationRunner
import org.github.mbarberot.mtg.grimoire.components.migration.VersionStore
import org.github.mbarberot.mtg.grimoire.components.migration.mtgjson.*
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.mp.KoinPlatform


fun main(args: Array<String>) {
    startKoin {
        modules(
            module {
                single { initJackson() }
                single<JadeConfiguration> { initializeJadeEngine() }
                single<MTGApi> { RestMTGApi(get()) }
            },
            module {
                single { IndexView(get()) }
                single { IndexRoute(get()) }
            },
            module {
                single<CardStore> { InMemoryCardStore() }
                single { CardView(get()) }
                single { CardsView(get()) }
                single { CardSearch(get()) }
                single { GetCardRoute(get(), get()) }
                single { GetCardsRoute(get(), get()) }
            },
            module {
                single<VersionStore> { InMemoryVersionStore() }
                single { TagGenerator() }
                single { CardUpdater(get(), get()) }
                single { MTGMigration(get(), get()) }
                single { MigrationRunner(get(), get()) }
            },
            module {
                single { Server(get(), get(), get()) }
            }
        )
    }

    Thread(KoinPlatform.getKoin().get<MigrationRunner>()).start()
    KoinPlatform.getKoin().get<Server>().start()
}

fun initJackson(): ObjectMapper {
    val mapper = jacksonObjectMapper()
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    return mapper
}

fun initializeJadeEngine(): JadeConfiguration {
    val jade = JadeConfiguration()
    jade.templateLoader = GrimoireTemplateLoader("/templates/")
    jade.isPrettyPrint = true
    jade.sharedVariables = mapOf(Pair("mana", ManaHelper()))
    return jade
}