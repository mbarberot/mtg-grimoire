package org.github.mbarberot.mtg.grimoire.model

import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import org.github.mbarberot.mtg.grimoire.misc.config.Configuration
import org.github.mbarberot.mtg.grimoire.model.managers.CardManager
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

    fun getCardManager(): CardManager {
        return CardManager(dbClient)
    }
}