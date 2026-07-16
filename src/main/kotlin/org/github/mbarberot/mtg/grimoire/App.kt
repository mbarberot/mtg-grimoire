package org.github.mbarberot.mtg.grimoire

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.jknack.handlebars.Handlebars
import com.github.jknack.handlebars.Helper
import com.github.jknack.handlebars.Options
import com.github.jknack.handlebars.TypeSafeTemplate
import com.github.jknack.handlebars.io.ClassPathTemplateLoader
import com.github.jknack.handlebars.io.FileTemplateLoader
import org.github.mbarberot.mtg.grimoire.business.searches.CardSearch
import org.github.mbarberot.mtg.grimoire.components.cards.*
import org.github.mbarberot.mtg.grimoire.components.index.IndexRoute
import org.github.mbarberot.mtg.grimoire.components.index.IndexView
import org.github.mbarberot.mtg.grimoire.components.migration.InMemoryVersionStore
import org.github.mbarberot.mtg.grimoire.components.migration.MigrationRunner
import org.github.mbarberot.mtg.grimoire.components.migration.VersionStore
import org.github.mbarberot.mtg.grimoire.components.migration.mtgjson.*
import org.github.mbarberot.mtg.grimoire.components.template.engine.helpers.ManaHelper
import org.github.mbarberot.mtg.grimoire.setup.SetupController
import org.github.mbarberot.mtg.grimoire.setup.SetupView
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.mp.KoinPlatform


fun main(args: Array<String>) {
    startKoin {
        modules(
            module {
                single { config() }
                single { initJackson() }
                single<Handlebars> { initializeHandlebars(get()) }
                single<MTGApi> { RestMTGApi(get()) }
            },
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



fun config(): AppConfig =
    AppConfig(
        host = "0.0.0.0",
        port = System.getenv("PORT")?.toInt() ?: DEFAULT_PORT,
        devMode = System.getProperty("app.devMode") == "true",
        devRoot = "${System.getProperty("user.dir")}/mtg-grimoire",
        language = "French"
    )

fun initJackson(): ObjectMapper {
    val mapper = jacksonObjectMapper()
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    return mapper
}

fun initializeHandlebars(appConfig: AppConfig): Handlebars {
    val loader = if (appConfig.devMode) {
        FileTemplateLoader("${appConfig.devRoot}/src/main/resources/templates")
    } else {
        ClassPathTemplateLoader("/templates")
    }

    return Handlebars(loader)
        .setCharset(Charsets.UTF_8)
        .registerHelper("mana", ManaHandlebarsHelper())
        .registerHelper("cardImage", CardImageHelper())
}

class CardImageHelper : Helper<Card> {
    override fun apply(card: Card?, options: Options?): String {
        val imageSrc = "https://gatherer.wizards.com/Handlers/Image.ashx?type=card&multiverseid=${card?.multiverseId ?: ""}"
        return """
            <img src="$imageSrc" alt="" />
        """
    }
}

class ManaHandlebarsHelper : Helper<String> {
    override fun apply(context: String?, options: Options?): String {
        return ManaHelper().mana(context)
    }
}

fun <C, T : TypeSafeTemplate<C>> Handlebars.compileTypesafe(
    location: String,
    typesafeClass: Class<T>,
): T = compile(location).`as`(typesafeClass)
