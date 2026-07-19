package org.github.mbarberot.mtg.grimoire

import org.github.mbarberot.mtg.grimoire.app.version.storage.api.VersionStore
import org.github.mbarberot.mtg.grimoire.app.version.storage.impl.InMemoryVersionStore
import org.github.mbarberot.mtg.grimoire.cards.provideCardModule
import org.github.mbarberot.mtg.grimoire.components.index.IndexRoute
import org.github.mbarberot.mtg.grimoire.components.index.IndexView
import org.github.mbarberot.mtg.grimoire.components.migration.MigrationRunner
import org.github.mbarberot.mtg.grimoire.components.migration.mtgjson.CardUpdater
import org.github.mbarberot.mtg.grimoire.components.migration.mtgjson.MTGMigration
import org.github.mbarberot.mtg.grimoire.components.migration.mtgjson.TagGenerator
import org.github.mbarberot.mtg.grimoire.components.setup.SetupController
import org.github.mbarberot.mtg.grimoire.components.setup.SetupView
import org.github.mbarberot.mtg.grimoire.database.provideDatabaseStorage
import org.github.mbarberot.mtg.grimoire.mtgapi.provideMTGApi
import org.github.mbarberot.mtg.grimoire.server.Server
import org.github.mbarberot.mtg.grimoire.server.provideServer
import org.github.mbarberot.mtg.grimoire.templating.provideTemplateEngine
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.mp.KoinPlatform


fun main() {
    startKoin {
        modules(
            module {
                single { config() }
            },
            provideDatabaseStorage(),
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
            provideCardModule(),
            module {
                single<VersionStore> { InMemoryVersionStore() }
                single { TagGenerator() }
                single { CardUpdater(get(), get(), get()) }
                single { MTGMigration(get(), get()) }
                single { MigrationRunner(get(), get()) }
            },
            provideServer()
        )
    }

    Thread(KoinPlatform.getKoin().get<MigrationRunner>()).start()
    KoinPlatform.getKoin().get<Server>().start()
}


fun config(): AppConfig {
    val devMode = System.getProperty("app.devMode") == "true"
    val port = System.getenv("PORT")?.toInt() ?: DEFAULT_PORT

    return if (devMode) {
        AppConfig(
            host = "0.0.0.0",
            port = port,
            devMode = true,
            devRoot = "${System.getProperty("user.dir")}/mtg-grimoire",
            language = "French",
            userStorage = "${System.getProperty("user.dir")}/mtg-grimoire/dev/user/Grimoire"
        )
    } else {
        AppConfig(
            host = LOCALHOST,
            port = port,
            devMode = false,
            language = "French",
            userStorage = "${System.getProperty("user.home")}/Documents/Grimoire"
        )
    }
}


