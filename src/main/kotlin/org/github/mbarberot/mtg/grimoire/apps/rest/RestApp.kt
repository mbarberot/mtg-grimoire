package org.github.mbarberot.mtg.grimoire.apps.rest

import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.conf.global
import com.github.salomonbrys.kodein.provider
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import org.github.mbarberot.mtg.grimoire.apps.rest.db.MongoCardStore
import org.github.mbarberot.mtg.grimoire.apps.rest.db.MongoVersionStore
import org.github.mbarberot.mtg.grimoire.core.Grimoire
import org.github.mbarberot.mtg.grimoire.core.stores.CardStore
import org.github.mbarberot.mtg.grimoire.core.stores.VersionStore
import org.jongo.JongoNative
import org.jongo.marshall.jackson.JacksonMapper

fun main(args: Array<String>) {
    val jongoInstance = connectMongo()
    val grimoire = Grimoire()

    with(Kodein.global) {
        addImport(Kodein.Module {
            bind<CardStore>() with provider { MongoCardStore(jongoInstance) }
            bind<VersionStore>() with provider { MongoVersionStore(jongoInstance) }
        })
        addImport(grimoire.coreModule)
    }

    grimoire.launch()

    Router(grimoire).initialize()
}

private fun connectMongo(): JongoNative {
    val mongoClient = MongoClient(MongoClientURI(System.getenv("DB_URL")))
    val database = mongoClient.getDatabase(System.getenv("DB_NAME"))
    val mapper = JacksonMapper.Builder().registerModule(KotlinModule()).build()
    val jongo = JongoNative(database, mapper)
    return jongo
}


