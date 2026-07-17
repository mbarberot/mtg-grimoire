package org.github.mbarberot.mtg.grimoire.mtgapi

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.github.mbarberot.mtg.grimoire.AppConfig
import org.github.mbarberot.mtg.grimoire.mtgapi.api.MTGApi
import org.github.mbarberot.mtg.grimoire.mtgapi.impl.MTGApiImpl
import org.koin.dsl.module

fun provideMTGApi() =
    module {
        single<MTGApi> { initializeMTGApi(get(), initJackson()) }
    }

fun initJackson(): ObjectMapper {
    val mapper = jacksonObjectMapper()
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    return mapper
}

fun initializeMTGApi(appConfig: AppConfig, mapper: ObjectMapper): MTGApi {
    return if (appConfig.devMode) {
        MTGApiImpl(baseUrl = "file://${appConfig.devRoot}/dev/mtgjson", mapper)
    } else {
        MTGApiImpl(baseUrl = "https://mtgjson.com", mapper)
    }
}