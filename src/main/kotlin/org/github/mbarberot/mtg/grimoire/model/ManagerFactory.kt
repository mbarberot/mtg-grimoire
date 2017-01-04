package org.github.mbarberot.mtg.grimoire.model

import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import org.github.mbarberot.mtg.grimoire.misc.config.Configuration
import org.github.mbarberot.mtg.grimoire.model.managers.CardManager
import org.jongo.JongoNative

class ManagerFactory(config: Configuration) {
    val dbClient: JongoNative
    
    init {
        val mongoClient = MongoClient(MongoClientURI(config.getDatabaseURL()))
        val database = mongoClient.getDatabase(config.getDatabaseName())
        dbClient = JongoNative(database)    
    }

    fun getCardManager(): CardManager {
        return CardManager(dbClient)
    }
}