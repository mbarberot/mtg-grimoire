package org.github.mbarberot.mtg.grimoire

import com.github.jknack.handlebars.Handlebars
import org.github.mbarberot.mtg.grimoire.business.searches.CardSearch
import org.github.mbarberot.mtg.grimoire.components.cards.*
import org.github.mbarberot.mtg.grimoire.components.index.IndexRoute
import org.github.mbarberot.mtg.grimoire.components.index.IndexView
import org.github.mbarberot.mtg.grimoire.components.migration.InMemoryVersionStore
import org.github.mbarberot.mtg.grimoire.components.migration.MigrationRunner
import org.github.mbarberot.mtg.grimoire.components.migration.VersionStore
import org.github.mbarberot.mtg.grimoire.components.migration.mtgjson.*
import org.github.mbarberot.mtg.grimoire.mtgapi.provideMTGApi
import org.github.mbarberot.mtg.grimoire.setup.SetupController
import org.github.mbarberot.mtg.grimoire.setup.SetupView
import org.github.mbarberot.mtg.grimoire.templating.provideTemplateEngine
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.mp.KoinPlatform


fun main(args: Array<String>) {
    startKoin {
        modules(
            module {
                single { config() }
            },
            provideTemplateEngine(),
            provideMTGApi(),
            module {
                single { IndexView(get()) }
                single { IndexRoute(get(), get()) }
            },
            module {
                single { SetupView(get()) }
                single { SetupController(get()) }
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
                single { CardUpdater(get(), get(), get()) }
                single { MTGMigration(get(), get()) }
                single { MigrationRunner(get(), get()) }
            },
            module {
                single {
                    Server(
                        get(),
                        get(),
                        get(),
                        get(),
                        get(),
                    )
                }
            }
        )
    }

    Thread(KoinPlatform.getKoin().get<MigrationRunner>()).start()
    KoinPlatform.getKoin().get<Server>().start()
}


fun config(): AppConfig {
    val devMode = System.getProperty("app.devMode") == "true"

    val userStorage = if (devMode) {
        "${System.getProperty("user.dir")}/dev/user"
    } else {
        "${System.getProperty("user.home")}/Documents/Grimoire"
    }

    return AppConfig(
        host = "0.0.0.0",
        port = System.getenv("PORT")?.toInt() ?: DEFAULT_PORT,
        devMode = devMode,
        devRoot = "${System.getProperty("user.dir")}/mtg-grimoire",
        language = "French",
        userStorage = userStorage
    )
}

