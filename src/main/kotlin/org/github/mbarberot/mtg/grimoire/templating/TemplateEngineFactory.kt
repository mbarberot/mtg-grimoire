package org.github.mbarberot.mtg.grimoire.templating

import com.github.jknack.handlebars.Handlebars
import com.github.jknack.handlebars.io.ClassPathTemplateLoader
import com.github.jknack.handlebars.io.FileTemplateLoader
import org.github.mbarberot.mtg.grimoire.AppConfig
import org.github.mbarberot.mtg.grimoire.templating.api.TemplateEngine
import org.github.mbarberot.mtg.grimoire.templating.handlebars.helpers.CardImageHelper
import org.github.mbarberot.mtg.grimoire.templating.handlebars.HandlebarsTemplateEngine
import org.github.mbarberot.mtg.grimoire.templating.handlebars.helpers.ManaHelper
import org.koin.dsl.module


fun provideTemplateEngine() =
    module {
        single { initializeHandlebars(get()) } // TODO use the template API
        single<TemplateEngine> { HandlebarsTemplateEngine(get()) }
    }

fun initializeHandlebars(appConfig: AppConfig): Handlebars {
    val loader = if (appConfig.devMode) {
        FileTemplateLoader("${appConfig.devRoot}/src/main/resources/templates")
    } else {
        ClassPathTemplateLoader("/templates")
    }

    return Handlebars(loader)
        .setCharset(Charsets.UTF_8)
        .registerHelper("mana", ManaHelper())
        .registerHelper("cardImage", CardImageHelper())
}
