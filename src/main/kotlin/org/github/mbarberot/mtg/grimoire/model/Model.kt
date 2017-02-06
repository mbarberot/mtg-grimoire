package org.github.mbarberot.mtg.grimoire.model

import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.conf.global
import com.github.salomonbrys.kodein.instance
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import org.github.mbarberot.mtg.grimoire.misc.config.Configuration
import org.github.mbarberot.mtg.grimoire.components.cards.CardStore
import org.github.mbarberot.mtg.grimoire.business.updates.Updater
import org.jongo.JongoNative
import org.jongo.marshall.jackson.JacksonMapper

class Model(config: Configuration) {
    val dbClient: JongoNative
    
    init {
        val mongoClient = MongoClient(MongoClientURI(config.getDatabaseURL()))
        val database = mongoClient.getDatabase(config.getDatabaseName())
        val mapper = JacksonMapper.Builder().registerModule(KotlinModule()).build()

        dbClient = JongoNative(database, mapper)
    }
}