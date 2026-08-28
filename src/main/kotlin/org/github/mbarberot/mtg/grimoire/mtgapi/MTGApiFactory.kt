package org.github.mbarberot.mtg.grimoire.mtgapi

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.github.mbarberot.mtg.grimoire.app.config.AppConfig
import org.github.mbarberot.mtg.grimoire.app.version.domain.Upgrader
import org.github.mbarberot.mtg.grimoire.mtgapi.api.MTGApi
import org.github.mbarberot.mtg.grimoire.mtgapi.impl.MTGApiImpl
import org.github.mbarberot.mtg.grimoire.mtgapi.update.MTGUpdater
import org.h2.tools.Upgrade
import org.koin.dsl.module

fun provideMTGApi() =
    module {
        single<MTGApi> { initializeMTGApi(get(), initJackson()) }
        single<Upgrader> { MTGUpdater(get(), get()) }
    }

fun initJackson(): ObjectMapper {
    val mapper = jacksonObjectMapper()
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    return mapper
}

fun initializeMTGApi(appConfig: AppConfig, mapper: ObjectMapper): MTGApi {
    return if (appConfig.devMode) {
        MTGApiImpl(baseUrl = "file://${appConfig.devRoot}/dev/mtgjson", mapper, appConfig)
    } else {
        MTGApiImpl(baseUrl = "https://mtgjson.com", mapper, appConfig)
    }
}